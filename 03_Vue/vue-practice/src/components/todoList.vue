<template>
  <div class="row" v-for="todo, index in todoList">
    <div>{{ index }}</div>
    <router-link :to="{ name: 'memo/id', params: { id: todo.id } }">{{ todo.title }}</router-link>
  </div>
</template>

<script setup>
defineProps({
    todoList: {
        type: Array,
        required: true
    }
})
</script>

<style scoped>
.row {
    display: flex;
    flex-direction: row;
}


</style>