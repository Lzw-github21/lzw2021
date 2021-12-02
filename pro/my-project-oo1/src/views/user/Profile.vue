<template>
  <div>
    <!--所有元素必须不能放在根节点下-->
    <h1>个人信息</h1>
<!--    {{$route.params.id}}-->
    {{id}}
    <!-- <div v-model="form">
      <p v-model="form.name">{{form.name}}</p>
    </div> -->
  </div>

</template>

<script>
export default {
  props:['id'],
  name: "UserProfile",
  beforeRouteEnter:(to,from,next)=>{ 
    console.log("进入路由之前");//在这里加载数据（axios）
    next(vm=>{
      vm.getData();//进入路由之前执行getData
    });
  },
  beforeRouteLeave:(to,from,next)=>{
    console.log("进入路由之后");
    next();
  },
  data(){
    return{
      form:{
        name:'',
        url:''
      }
    }
  },
  methods:{
    getData:function (){
      this.axios({
        method:'get',
        url:'http://localhost:8080/static/mock/data.json'
      }).then(function (response){
          console.log(response);


      })
    }
  }
}
</script>

<style scoped>

</style>