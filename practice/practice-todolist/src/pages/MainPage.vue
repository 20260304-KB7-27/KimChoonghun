<script setup>
import TodoLists from '@/components/todoLists.vue';
import NewTodo from '../components/NewTodo.vue';
import {computed, ref} from 'vue';

const todoLists = ref([
    {
        id: 1,
        title: "밥 먹기",
        finished: false
    },
    {
        id: 2,
        title: "집 가기",
        finished: false
    },
    {
        id: 3,
        title: "모듈평가 보기",
        finished: false
    },
    {
        id: 4,
        title: "헬스장 가기",
        finished: false
    }
]);

const toggleItem = (data) => {
    console.log(todoLists.value)
    console.log(data);
    const item = todoLists.value.find((t) => (t.id == data.id))
    item.finished = !item.finished;
}

const deleteItem = (data) => {
    const idx = todoLists.value.findIndex((t) => (t.id == data.id))
    console.log(idx);
    todoLists.value.splice(idx, 1);
}

const addItem = (data) => {
    const id = todoLists.value.length+1
    todoLists.value.push({id: id, title: data.title, finished:false});
}

const totalItemsNum = computed(() => todoLists.value.length);
const totalFinishNum = computed(() => {
    let i = 0;
    for (var x = 0; x < todoLists.value.length; x++) if (todoLists.value[x].finished) i++;
    return i;
})
</script>

<template>
  <div class="todoList-bg">
    <h1>TodoLists for 모듈평가</h1>
    <NewTodo @add="addItem"></NewTodo>
    <p>완료 / 전체 : {{ totalFinishNum }} / {{ totalItemsNum }}</p>
    <TodoLists :todo-lists="todoLists" @tog="toggleItem" @del="deleteItem"></TodoLists>
  </div>
</template>

<style scoped>
.todoList-bg {
    display: flex;
    flex-direction: column;
    text-align: center;
    margin: 0 auto;
    width: 60%;
}

.todoList-bg > h1 {
    padding: 10px;
    border: 2px black solid;
}
</style>
