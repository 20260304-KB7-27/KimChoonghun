<script setup>
import { ref, computed } from 'vue';

defineProps({
    todoItem: {
        type: Object,
        required: true
    }
})

const emits = defineEmits(['toggle', 'delete']);

</script>

<template>
  <div class="TodoItems"> 
    <span class="item-id">{{ todoItem.id }}</span>
    <div 
        :class="{'text-dashed': todoItem.finished}";
        class="title"
    >{{ todoItem.title }}</div>
    <!-- <div id="toggle">{{ todoItem.finished }}</div> -->
    <button class="toggle" :class="{'toggle-cancel': todoItem.finished}" @click="emits('toggle', {id:todoItem.id})">{{ !todoItem.finished ? '완료' : '취소'}}</button>
    <button class="delete" @click="emits('delete', {id:todoItem.id})">제거</button>
  </div>
</template>

<style scoped>
.text-dashed {
    text-decoration: line-through;
    color: gray;
}
.TodoItems {
    margin-top: 10px;
    padding: 10px;
    border-radius: 10px;
    display: flex;
    width: 100%;
    justify-content: space-between;
    border: 1px black solid;
}
.title {
    text-align: start;
    width: 100%;
    padding: 10px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: inherit;
}
.item-id, .toggle {
    padding: 10px;
}
button.toggle {
    width: 60px;
    margin-right: 10px;
    color: rgb(51, 51, 51);
    font-weight: bold;
    background-color: rgb(229, 243, 255);
    border: 2px solid skyblue;
    border-radius: 10px;
}
button.toggle-cancel {
    background-color: rgb(255, 232, 198);
    border: 2px solid rgb(235, 172, 135);
}
button.delete {
    width: 60px;
    color: rgb(252, 252, 252);
    font-weight: bold;
    background-color: rgb(255, 151, 132);
    border: 2px solid rgb(235, 135, 135);
    border-radius: 10px;
}
button:hover { 
    opacity: 0.8;
    cursor: pointer;
}
</style>