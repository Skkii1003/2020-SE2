<template>
    <div>
        <v-card :color="'#BCAAA4'">
            主题：{{postTitle}}
            <br/>
            内容：{{postContent}}
            <br/>
            发帖者昵称：
            {{postName}}
        </v-card>
        <br/>
        <br/>

        <v-card v-for="(itemm,index) in comments_list" :key="itemm.commenttime">
            {{itemm.content}}
            <br/>
            <br/>
            回复者: {{nameList[index]}} <br/>时间: {{(itemm.commenttime)}}
        </v-card>
        <v-card :color="'#BCAAA4'" dark width="600" height="200" class="ma-4 pa-2">
            <v-card-text>
                <label>
                <textarea cols="60" rows="5" v-model="content">
                </textarea>
                </label>
                <v-btn @click="postPost">发布回复</v-btn>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
    import {addComment, getComments} from "../../api/post";
    import {getUser} from "../../api/user";
    import {addNotice} from "../../api/post";

    export default {
        name: "CourseComments",
        data(){
            return{
                href:window.location.href,
                content:"",
                comments_list:[],
                nameList:[],
                cname:"",
                userInfo: {
                    id: 0,
                    username: "",
                    userRole: "",

                },
                postuid:0,
                postContent:"",
                postTitle:"",
                postName:"",
                postRole:"",
                time:0,
            }
        },
        methods:{

            getUserName(uid){

                getUser(uid).then(res => {
                    var userName;
                    console.log(res);
                    userName=res.uname|| {};
                    console.log("userName: "+userName);
                    return userName;
                });

            },
            refreshUserInfo() {
                const userId = window.localStorage.getItem("userId");
                getUser(userId).then(res => {
                    this.userInfo.id = res.id || {};
                    this.userInfo.username=res.uname|| {};
                    this.userInfo.role=res.role|| {};
                });

            },
            postPost(){
                this.refreshUserInfo();
                if(this.userInfo.id===0){
                    alert("用户未登录")
                }
                else if(this.content==="") {alert("回复不能为空");}
                else {
                    this.time=new Date(Date.now());
                    addComment(this.postuid,this.$route.query.courseId, this.content, this.$route.query.time, this.userInfo.id,this.time);

                    var url=window.location.href;
                    url=url.substr(23);
                    console.log(url);
                    addNotice(this.userInfo.id,this.postuid,this.time,url);
                    this.$router.go(0);
                }


            },
            getNames(){
                for (var i=0;i<this.comments_list.length;i++){
                    console.log(i);
                    getUser(this.comments_list[i].commentuid).then(res => {
                        var userName;
                        userName=res.uname|| {};
                        console.log("userName: "+userName);
                        this.nameList.push(userName);
                    });
                }
            },
        },
        mounted() {
            this.refreshUserInfo();
            this.postuid=this.$route.query.uid;
            this.postContent=this.$route.query.content;
            this.postTitle=this.$route.query.title;
            console.log("title: "+this.postTitle);
            getUser(this.postuid).then(res => {

                this.postName=res.uname|| {};
            });
            getComments(this.$route.query.courseId,this.$route.query.uid,this.$route.query.time).then(res=>{
                console.log(res);
                this.comments_list=res;
                this.getNames();
            });
        }
    }
</script>

<style scoped>

</style>