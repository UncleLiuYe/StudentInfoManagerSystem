<template>
  <div>
    <table border="1" style="text-align: center;margin: 0 auto;">
      <thead>
      <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>班级</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for='(item,index) in tabledata' :key="index">
        <td>{{ item.studentxuehao }}</td>
        <td>{{ item.studentname }}</td>
        <td>{{ item.studentsex }}</td>
        <td>{{ item.studentclassid.classname }}</td>
        <td>
          <button @click="updateStudent(item.studentid)">更新</button>
          |
          <button @click="deleteStudent(item.studentid)">删除</button>
        </td>
      </tr>
      </tbody>
    </table>
    <br/>
    <button style="display:block;margin: 0 auto;" @click="addStudent">添加学生</button>
    <hr/>
    <button style="display:block;margin: 0 auto;" @click="logout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: "ushowAllStudent",
  created() {
    this.$axios.get("/user/getAllStudent").then(res => {
      this.tabledata = res.data;
    });
  },
  data() {
    return {
      tabledata: [],
    }
  },
  methods: {
    addStudent() {
      this.$router.push("/uaddStudent");
    },
    updateStudent(id) {
      this.$router.push({path: "uupdateStudent", query: {id: id}});
    },
    deleteStudent(id) {
      if (confirm('确定要删除吗')) {
        this.$axios.post("/user/deleteStudent?id=" + id).then(res => {
          alert(res.data);
          window.location.reload();
        }).catch(res => {
          alert(res);
        });
      }
    },
    logout() {
      if (confirm("确定退出系统吗?")) {
        this.$axios.post("/logout").then(res => {
          if (res.data.msg === "退出成功") {
            alert(res.data.msg);
            window.sessionStorage.removeItem("id");
            window.sessionStorage.removeItem("role");
            this.$router.push("/");
          } else {
            alert("退出失败！")
          }
        }).catch(res => {
          alert(res);
        })
      }
    }
  }
}
</script>

<style scoped>
</style>