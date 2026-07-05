<script setup>
import { ref, computed } from 'vue';
import Todos from './Todos.vue';

defineProps({
    todoLists: {
        type: Array,
        required: true
    }
})

const emits = defineEmits(['tog', 'del'])

const toggleItem = (data) => {
    emits('tog', data)
    // console.log(data);
}

const deleteItem = (data) => {
    emits('del', data)
}
</script>

<template>
  <div>
    <div v-for="t in todoLists">
        <Todos :todo-item="t" @toggle="toggleItem" @delete="deleteItem"></Todos>
    </div>
  </div>
</template>

<style scoped></style>
