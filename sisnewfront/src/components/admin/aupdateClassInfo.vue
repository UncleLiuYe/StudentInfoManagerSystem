<template>
  <div>
    <div class="main">
      <h1>更新班级</h1>
      <form @submit.prevent="checkAndSubmit">
        <label>
          班级名称：<input type="text" v-model="classname">
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
  name: "aupdateClassInfo",
  created() {
    this.$axios.post("/admin/getClassInfoById?id=" + this.$route.query.id).then(res => {
      this.classname = res.data.classname;
    }).catch(res => {
      alert(res);
    })
  },
  data() {
    return {
      classname: ""
    }
  }, methods: {
    checkAndSubmit() {
      var data = {
        classid: this.$route.query.id,
        classname: this.classname,
      };
      if (data.classname === "") {
        alert("请将数据填写完整！");
      } else {
        this.$axios.post("/admin/updateClassInfo", data).then(res => {
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