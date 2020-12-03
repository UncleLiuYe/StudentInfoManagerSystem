<template>
  <div>
    <div class="main">
      <h1>更新学生</h1>
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
          性别：<input type="radio" v-model="studentsex" value="男">男 <input type="radio" v-model="studentsex" value="女">女
        </label>
        <br/><br/>
        <label>
          班级：<select v-model="studentclassid">
          <option v-for="(item,index) in classList" :key="index" :value="item">{{ item.classname }}</option>
        </select>
        </label>
        <br/><br/>
        <label>
          <input type="submit" value="更新"> <input type="reset" value="重置">
        </label>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "aupdateStudent",
  created() {
    this.$axios.get("/admin/getAllClassInfo").then(res => {
      this.classList = res.data;
    }).catch(res => {
      alert(res);
    });
    this.$axios.post("/admin/getStudentById?id=" + this.$route.query.id).then(res => {
      this.studentname = res.data.studentname;
      this.studentsex = res.data.studentsex;
      this.studentxuehao = res.data.studentxuehao;
      this.studentclassid = res.data.studentclassid;
    }).catch(res => {
      alert(res)
    })
  },
  data() {
    return {
      classList: [],
      studentxuehao: "",
      studentname: "",
      studentsex: "男",
      studentclassid: "",
    }
  },
  methods: {
    checkAndSubmit() {
      var data = {
        studentid: this.$route.query.id,
        studentxuehao: this.studentxuehao,
        studentname: this.studentname,
        studentsex: this.studentsex,
        studentclassid: this.studentclassid,
      };
      if (data.studentname === "" || data.studentxuehao === "" || data.studentsex === "" || data.studentclassid === "") {
        alert("请将数据填写完整！");
      } else {
        this.$axios.post("/admin/updateStudent", data).then(res => {
          alert(res.data);
          this.$router.push("/ashowAllStudent");
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