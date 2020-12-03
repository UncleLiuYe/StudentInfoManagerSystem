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
    <table border="1" style="text-align: center;margin: 0 auto;">
      <thead>
      <tr>
        <th>班级名称</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for='(item,index) in classtableData' :key="index">
        <td>{{ item.classname }}</td>
        <td>
          <button @click="updateClassInfo(item.classid)">更新</button>
          |
          <button @click="deleteClassInfo(item.classid)">删除</button>
        </td>
      </tr>
      </tbody>
    </table>
    <br/>
    <button style="display:block;margin: 0 auto;" @click="addClassInfo">添加班级</button>
    <hr/>
    <button style="display:block;margin: 0 auto;" @click="logout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: "ashowAllStudent",
  created() {
    this.$axios.get("/admin/getAllStudent").then(res => {
      this.tabledata = res.data;
    }).catch(res => {
      alert(res);
    });
    this.$axios.get("/admin/getAllClassInfo").then(res => {
      this.classtableData = res.data;
    }).catch(res => {
      alert(res);
    })
  },
  data() {
    return {
      tabledata: [],
      classtableData: [],
    }
  },
  methods: {
    addStudent() {
      this.$router.push("/aaddStudent");
    },
    updateStudent(id) {
      this.$router.push({path: "aupdateStudent", query: {id: id}});
    },
    deleteStudent(id) {
      if (confirm('确定要删除吗')) {
        this.$axios.post("/admin/deleteStudent?id=" + id).then(res => {
          alert(res.data);
          window.location.reload();
        }).catch(res => {
          alert(res);
        });
      }
    },
    addClassInfo() {
      this.$router.push("/aaddClassInfo");
    },
    updateClassInfo(id) {
      this.$router.push({path: "aupdateClassInfo", query: {id: id}});
    },
    deleteClassInfo(id) {
      if (confirm('确定要删除吗')) {
        this.$axios.post("/admin/deleteClassInfo?id=" + id).then(res => {
          alert(res.data);
          window.location.reload();
        }).catch(res => {
          alert(res);
        });
      }
    }, logout() {
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