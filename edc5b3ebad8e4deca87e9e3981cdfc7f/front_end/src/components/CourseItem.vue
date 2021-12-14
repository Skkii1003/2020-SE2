<template>
  <v-card :color="courseColor" dark width="380" height="260" class="ma-4 pa-2">
    <v-card-title class="headline">
      {{ courseName }}
      <v-chip
        small
        class="ml-4"
        v-show="status === 1 || status === 0"
        :color="chipColor[status]"
      >
        {{ chip[status] }}
      </v-chip>
    </v-card-title>

    <v-card-text class="text">
      【{{ type }}课程】
      {{ text }}
    </v-card-text>

    <v-card-actions>
      <v-btn text v-show="status === 0 || status === 1" @click="handleStudy"
        >学习课程</v-btn
      >
      <v-btn text v-show="status === -1 || !hasLogin" @click="handlePeek"
        >浏览课程</v-btn
      >
      <v-btn
        text
        v-show="status === -1 || (status === 0 && !bought)"
        @click="buyCourse"
        >{{ cost === 0 ? "免费购买" : "购买课程" }}</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "CourseItem",
  props: {
    courseName: {
      type: String,
      default: "SubjectName",
    },
    courseId: {
      type: Number,
      default: 0,
    },
    description: {
      type: String,
      default: "课程简介",
    },
    type: {
      type: String,
      default: "初级",
    },
    cost: {
      type: Number,
      default: 0,
    },
    bought: {
      type: Boolean,
      default: false,
    },
    manageable: {
      type: Boolean,
      default: false,
    },
    hasLogin: {
      type: Boolean,
      default: true,
    },
    courseColor: {
      type: String,
      default: "#BCAAA4",
    },
  },
  data() {
    return {
      chip: ["免费", "已购"],
      chipColor: ["success", "primary"],
    };
  },
  methods: {
    buyCourse() {
      this.$emit("buy-course", this.courseId, this.courseName, this.cost);
    },
    handleStudy() {
      if (this.hasLogin) {
        this.$router.push(`/student/course/${this.courseId}`);
      } else {
        this.$emit("buy-course", this.courseId, this.courseName, this.cost);
      }
    },
    handlePeek() {
      this.$router.push(`/student/peek/${this.courseId}`);
    },
  },
  computed: {
    text: function() {
      return this.description.length < 60
        ? this.description
        : this.description.substring(0, 60) + "...";
    },

    // 0 免费  1 已购
    status: function() {
      if (this.cost === 0) {
        return 0;
      } else if (this.bought) {
        return 1;
      }
      return -1;
    },
  },
});
</script>

<style scoped>
.text {
  height: 85px;
  overflow: hidden;
}
</style>
