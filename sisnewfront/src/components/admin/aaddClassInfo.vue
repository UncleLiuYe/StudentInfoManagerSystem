<template>
  <div>
    <div class="main">
      <h1>添加班级</h1>
      <form @submit.prevent="checkAndSubmit">
        <label>
          班级名称：<input type="text" v-model="classname">
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
  name: "aaddClassInfo",
  data() {
    return {
      classname: ""
    }
  }, methods: {
    checkAndSubmit() {
      var data = {
        classname: this.classname,
      };
      if (data.classname === "") {
        alert("请将数据填写完整！");
      } else {
        this.$axios.post("/admin/addClassInfo", data).then(res => {
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