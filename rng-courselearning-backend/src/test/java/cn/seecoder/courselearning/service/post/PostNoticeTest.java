package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.mapperservice.post.PostNoticeMapper;
import cn.seecoder.courselearning.po.post.PostNotice;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.PostNoticeVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostNoticeTest {

    @Resource
    PostService postService;
    @Resource
    PostNoticeMapper postNoticeMapper;

    static PostNoticeVO testData_1 =
            new PostNoticeVO(0,1,"0","url");
    static ResultVO<PostNoticeVO> testData_2 =
            new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功回复！",testData_1);

    //创建一个正确对象，调用接口创建一个测试对象，然后进行断言比较
    @Test
    public void addNoticeTest(){
        ResultVO<PostNoticeVO> res =
                postService.addNotice(0,1,"0","url");
        Assert.assertEquals(testData_2.getData().receiverUid,res.getData().receiverUid);
        Assert.assertEquals(testData_2.getData().senderUid,res.getData().senderUid);
        Assert.assertEquals(testData_2.getData().senderUid,res.getData().senderUid);
        Assert.assertEquals(testData_2.getData().url,res.getData().url);
    }

    @Test
    public void getNoticeTest(){
        List<PostNoticeVO> res = postService.getNotice(1);
        List<PostNotice> postNotices = postNoticeMapper.get(1);
        List<PostNoticeVO> pnVO = new ArrayList<>();
        for (PostNotice pn: postNotices){
            pnVO.add(new PostNoticeVO(pn.senderUid,pn.receiverUid,pn.sendTime,pn.url));
        }
        for (int i=0;i<Math.min(pnVO.size(),res.size());i++){
            Assert.assertEquals(pnVO.get(i).url,res.get(i).url);
            Assert.assertEquals(pnVO.get(i).senderUid,res.get(i).senderUid);
            Assert.assertEquals(pnVO.get(i).receiverUid,res.get(i).receiverUid);
            Assert.assertEquals(pnVO.get(i).sendTime,res.get(i).sendTime);

        }
    }

    @Test
    public void checkNoticeTest(){
        PostNotice pn = postNoticeMapper.selectByTime("0").get(0);
        PostNoticeVO pnVO =
                new PostNoticeVO(pn.senderUid,pn.receiverUid,pn.sendTime,pn.url);
        PostNoticeVO res = postService.checkNotice("0");
        Assert.assertEquals("",pnVO.sendTime,res.sendTime);
        Assert.assertEquals("",pnVO.url,res.url);
        Assert.assertEquals("",pnVO.receiverUid,res.receiverUid);
        Assert.assertEquals("",pnVO.senderUid,res.senderUid);

    }

}
