<template>
    <div>
        <v-card :color="'#000000'" dark width="600" class="ma-4 pa-2">
            <v-card-text >
                {{courseTitle}}
            </v-card-text>
            <v-card-text >
                {{courseIntro}}
            </v-card-text>
        </v-card>
        <CourseDiscuss  v-bind:courseId="this.$route.params.courseId"></CourseDiscuss>

        <v-card  v-show="notice_list.length" :color="'#BCDAA4'" dark width="600"  class="ma-4 pa-2">
            回帖通知：
            <v-card v-for="(item,index) in notice_list" :key="item.sendTime" @click="handleNotice(item.sendTime,item.url)">
                收到来自{{noticeNameList[index]}}的回复
                <br/>
                时间：{{(item.sendTime)}}
            </v-card>
        </v-card>



            <v-container>
                <v-btn class="mx-2 align-self-center"
                       dark
                       small
                       @click="presentByTime">按时间排序 </v-btn>
                <v-btn class="mx-2 align-self-center"
                       dark
                       small
                       @click="presentByReply"> 按最新回复排序 </v-btn>
            <v-card v-for="(item,index) in page_list" :key="item.time"  @click="handlePost(item.uid,item.time,item.comment,item.title)">
                {{nameList[index]}}发表了: {{item.title}}  时间：{{(item.time)}}
                <br/>
                内容：{{item.comment}}

            </v-card>
                <v-btn class="mx-2 align-self-center"
                       dark
                       small
                       @click="getLastPage"> 上一页 </v-btn>
                <v-btn class="mx-2 align-self-center"
                       dark
                       small
                       @click="getNextPage"> 下一页 </v-btn>
        </v-container>
    </div>

</template>

<script>
    import CourseDiscuss from "./CourseDiscuss";
    import { getPosts} from "../../api/post";
    import {getUser} from "../../api/user";
    import {deleteNotice, getNotice} from "../../api/post";
    import {getCourseByPrimaryKey} from "../../api/course";

    export default {
        name: "discussLayout",
        components: {CourseDiscuss},
        data(){
            return{
                courseTitle:"",
                index:0,
                page_list:[],
                time_list:[],
                reply_list:[],
                courseId:this.$route.params.courseId,
                notice_list:[],
                list:[
                ],
                comments_list:[],
                cname:"",
                isReload:false,
                nameList:[],
                noticeNameList:[],
                courseIntro:""
            }
        },
        methods:{
            refreshPageList(){
                console.log("refreshPageList");

                this.page_list=[];
                for(var i=0;i<10&&this.index*10+i<this.list.length;i++){
                    this.page_list.push(this.list[this.index*10+i]);
                }
            },
            presentByTime(){
                this.list=this.sortByT(this.time_list);
                this.index=0;
                this.refreshPageList();
            },
            presentByReply(){
                this.list=this.sortByR(this.reply_list);
                this.index=0;
                this.refreshPageList();
            },

            getLastPage(){
                if(this.index>0){
                    this.index=this.index-1;
                    this.refreshPageList();
                }
            },
            getNextPage(){
                console.log("nextPage");
                if(this.index<this.list.length/10){
                    this.index=this.index+1;
                    this.refreshPageList();
                }
            },
            sortByT(llist){
                var list=llist;
                for(var i=0;i<list.length-1;i++){
                    for(var j=0;j<list.length-i-1;j++){
                        if(list[j].time<list[j+1].time){
                            var tem=list[j];
                            list[j]=list[j+1];
                            list[j+1]=tem;
                        }
                    }
                }
                return list;
            },
            sortByR(llist){

                var list=llist;
                for(var i=0;i<list.length-1;i++){
                    for(var j=0;j<list.length-i-1;j++){
                        if(list[j].last_ctime<list[j+1].last_ctime){
                            var tem=list[j];
                            list[j]=list[j+1];
                            list[j+1]=tem;
                        }
                    }
                }
                console.log(llist);
                console.log(list);
                return list;
            },
            initPageList(){
                console.log("initPageList");
                this.page_list=[];
                console.log("index: "+this.index);
                for(var i=0;i<10&&i<this.list.length;i++){
                    console.log("index: "+this.list[i]);
                    this.page_list.push(this.list[i]);
                }

            },
            handleNotice(sendTime,url){
                console.log(url);
                deleteNotice(sendTime).then(res=>{
                    console.log(res);
                    console.log("删除回帖通知");

                })
                this.$router.push(url);
            },
            reload() {
                this.$router.go(0);
            },
            setName(){
                this.cname+=this.$route.params.courseId
            },
            getAllPosts(){
                console.log("get");
                getPosts(this.$route.params.courseId).then(res=>{
                    console.log(res);
                    this.list=res;
                    this.initPageList();
                    this.time_list=this.sortByT(res);
                    this.reply_list=this.sortByR(res);
                    this.getNames();
                });

            },
            handlePost(uid,time,content,title){
                console.log("discussLayout uid: "+uid);
                this.$router.push(`/courseComments/${this.courseId}/${time}?&content=${content}&time=${time}&courseId=${this.courseId}&uid=${uid}&title=${title}`);
            },
            getNoticeF(){
                const userId = window.localStorage.getItem("userId");
                getNotice(userId).then(res=>{
                    this.notice_list=res;
                    this.getNoticeNames();
                })
            },
            getNames(){
                for (var i=0;i<this.list.length;i++){
                    getUser(this.list[i].uid).then(res => {
                        var userName;
                        userName=res.uname|| {};

                        this.nameList.push(userName);
                    });
                }
            },
            getNoticeNames(){
                for (var i=0;i<this.notice_list.length;i++){
                    getUser(this.notice_list[i].senderUid).then(res => {
                        var userName;
                        userName=res.uname|| {};
                        console.log("noticeUserName: "+userName);
                        this.noticeNameList.push(userName);
                    });
                }

            }
        },
      mounted(){
          getCourseByPrimaryKey(this.$route.params.courseId).then(res=>{
               this.courseTitle=res.name;
              this.courseIntro=res.intro;
            });
        this.getAllPosts();
        this.getNoticeF();
      }
    }

</script>

<style scoped>

</style>
