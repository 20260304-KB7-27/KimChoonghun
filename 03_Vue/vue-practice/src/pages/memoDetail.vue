<template>
  <h1>{{ memo.title }}</h1>
  <br>
  <p>{{ memo.content }}</p>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import {useRouter,useRoute} from 'vue-router';

const memo = ref({});
async function getData() {
    const apiURL = 'http://localhost:3000/memo'
    // 4-1) router를 설정하세요.
    const router = useRouter();
    // 4-2) currentRoute를 설정하세요.
    const currentRoute = useRoute();
    // 4-3) 쿼리 파라미터에서 id 추출하여 id를 설정하세요.
    const id = currentRoute.params.id;
    const response = await axios.get(`${apiURL}/${id}`);
    console.log(response.data)
    memo.value = response.data;
}

getData();
</script>

<style scoped>



</style>