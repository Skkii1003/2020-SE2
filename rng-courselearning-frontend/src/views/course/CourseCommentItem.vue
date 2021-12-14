<template>
    <v-card :color="'#BCAAA4'" dark width="600" height="260" class="ma-4 pa-2">
        <v-card-text>
            <label>
                <textarea cols="60" rows="5" v-model="content">
                </textarea>
            </label>
            <v-btn @click="postPost">发布回复</v-btn>
        </v-card-text>
    </v-card>
</template>

<script>
    import { getUser } from "@/api/user";
    import {addComment} from "../../api/post";
    export default {
        name: "CourseCommentItem",
        data(){
            return {
                title:"",
                content:"",
                userInfo: {
                    id: 0,
                    username: "",
                    userRole: "",

                }
            }
        },
        props:['courseId'],
        methods:{
            postPost(){
                this.refreshUserInfo();
                if(this.userInfo.id===0){
                    alert("用户未登录")
                }
                else if(this.title===""||this.content==="") alert("标题或内容不能为空")
                else {
                    addComment(this.userInfo.id, this.courseId, this.title, this.content, new Date(Date.now())).then(res => {
                        console.log(res);
                    });
                }
            },
            refreshUserInfo() {
                const userId = window.localStorage.getItem("userId");
                getUser(userId).then(res => {
                    console.log(this.title);
                    this.userInfo.id = res.id || {};
                    this.userInfo.username=res.uname|| {};
                    this.userInfo.role=res.role|| {};
                });
            }
        },
        mounted() {
            this.refreshUserInfo();
        }
    }

</script>

<style scoped>

</style>
