<template>
  <div>
    <div class="main">
      <h1>登录</h1>
      <form @submit.prevent="checkAndSubmit">
        <label>
          用户名：<input type="text" v-model="username">
        </label>
        <br/><br/>
        <label>
          密码：<input type="password" v-model="password">
        </label><br/><br/>
        <label>
          <input type="submit" value="登录"> <input type="reset" value="重置">
        </label>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
    }
  },
  methods: {
    checkAndSubmit() {
      if (this.username === "" || this.password === "") {
        alert("请将用户名密码输入完整！");
      } else {
        this.$axios.post("/login?username=" + this.username + "&password=" + this.password).then(res => {
          if (res.status === 200) {
            sessionStorage.setItem("id", res.data.msg.id);
            for (var i = 0; i < res.data.msg.roles.length; i++) {
              if (res.data.msg.roles[i].name === "ROLE_admin") {
                sessionStorage.setItem("role", "admin");
                break;
              } else if (res.data.msg.roles[i].name === "ROLE_user") {
                sessionStorage.setItem("role", "user");
              }
            }
            if (sessionStorage.getItem("role") === "admin") {
              this.$router.push("/ashowAllStudent");
            } else if (sessionStorage.getItem("role") === "user") {
              this.$router.push("/ushowAllStudent");
            }
          } else {
            alert("登陆失败");
          }
        }).catch(res => {
          alert(res);
        });
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