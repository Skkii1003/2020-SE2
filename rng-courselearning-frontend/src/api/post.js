import axios from "axios";
import { POST_MODULE} from "./_prefix";

export const addPost = (uid, courseId, title, comment, time )=>{
    console.log("调用course.js中的addPost");
    return axios
        .post(`${POST_MODULE}/add_post?courseId=${courseId}&uid=${uid}&title=${title}&content=${comment}&time=${time}`)
        .then(res=>{
            return res.data;
        })
};
export const getPosts = (courseId)=>{
    console.log("调用post.js中的getPosts");
    return axios
        .get(`${POST_MODULE}/get_posts?courseId=${courseId}`)
        .then(res=>{
            console.log("post.js中的getPosts");
            console.log(res);
            return res.data;
        })
};
export const addComment = (postuid, courseId, content, posttime, commentuid,commenttime )=>{
    console.log("调用course.js中的addComment");
    return axios
        .post(`${POST_MODULE}/add_comment?courseId=${courseId}&postuid=${postuid}&content=${content}&posttime=${posttime}&commentuid=${commentuid}&commenttime=${commenttime}`)
        .then(res=>{
            return res.data;
        })
};
export const getComments = (courseId,uid,time)=>{
    console.log("调用post.js中的getComments");
    return axios
        .get(`${POST_MODULE}/get_comments?courseId=${courseId}&postuid=${uid}&posttime=${time}`)
        .then(res=>{
            return res.data;
        })
};
export const getNotice = (uid) => {
    return axios.get(`${POST_MODULE}/get_notice?receiverUid=${uid}`).then(res => {
        return res.data;
    });
};

export const addNotice=(senderUid,receiverUid,sendTime,url)=>{
    console.log("调用post.js中的addNotice: url: " +url);
    return axios.post(`${POST_MODULE}/add_notice?senderUid=${senderUid}&receiverUid=${receiverUid}&sendTime=${sendTime}`,{
        senderUid,
        receiverUid,
        sendTime,
        url
    }).then(res => {
        return res.data;
    });
}

export const deleteNotice = (sendTime) => {
    return axios.post(`${POST_MODULE}/check_notice?sendTime=${sendTime}`).then(res => {
        return res.data;
    });
};