<template>
  <div>
    <div class="main">
      <h1>添加学生</h1>
      <form @submit.prevent="checkAndSubmit">
        <label>
          学号：<input type="text" v-model="studentxuehao">
        </label>
        <br/><br/>
        <label>
          姓名：<input type="text" v-model="studentname">
        </label>
        <br/><br/>
        <label>
          性别：<input type="text" v-model="studentsex">
        </label>
        <br/><br/>
        <label>
          班级：<select v-model="studentclassid">
          <option v-for="(item,index) in classList" :key="index" :value="item">{{ item.classname }}</option>
        </select>
        </label>
        <br/><br/>
        <label>
          <input type="submit" value="添加"> <input type="reset" value="重置">
        </label>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "uaddStudent",
  created() {
    this.$axios.get("/admin/getAllClassInfo").then(res => {
      this.classList = res.data;
    }).catch(res => {
      alert(res);
    });
  },
  data() {
    return {
      classList: [],
      studentxuehao: "",
      studentname: "",
      studentsex: "",
      studentclassid: "",
    }
  },
  methods: {
    checkAndSubmit() {
      var data = {
        studentxuehao: this.studentxuehao,
        studentname: this.studentname,
        studentsex: this.studentsex,
        studentclassid: this.studentclassid,
      };
      if (data.studentname === "" || data.studentxuehao === "" || data.studentsex === "" || data.studentclassid === "") {
        alert("请将数据填写完整！");
      } else {
        this.$axios.post("/user/addStudent", data).then(res => {
          alert(res.data);
          this.$router.push("/ushowAllStudent");
        }).catch(res => {
          alert(res);
        })
      }
    }
  }
}
</script>

<style scoped>
.main {
  display: flex;
  flex-flow: column;
  width: 500px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
</style>