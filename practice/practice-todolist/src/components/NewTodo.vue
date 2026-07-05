<script setup>
import { ref, computed } from 'vue';

const newTodo = ref("");
const emits = defineEmits(['add'])
const addTodo = () => {
    emits('add', {title: newTodo.value});
}
</script>

<template>
  <div class="newTodo">
    <input v-model="newTodo"/>
    <button @click="addTodo">추가</button>
  </div>
</template>

<style scoped>

div#newTodo {
    width: 100%;
    display: flex;
    justify-content: space-between;
}
input {
    padding: 10px;
    border-radius: 10px;
    width: 60%;
}
button {
    border-radius: 10px;
    padding: 10px;
    cursor: pointer;
}
</style>