import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

export const useTodoListStore = defineStore('todolist', () => {
  const todoList = ref([]);
  const totalItemsNum = computed(() => todoList.value.length);
  const BASE_URL = 'http://localhost:3000/todoList';
  const totalFinishNum = computed(() => {
      let i = 0;
      for (var x = 0; x < todoList.value.length; x++) if (todoList.value[x].finished) i++;
      return i;
  })

  const fetchTodoLists = async () => { // get 요청 
    try {
      const response = await axios.get(BASE_URL);
      todoList.value = response.data;
    } catch {
      alert("오류 발생!")
    }
  }

  const deleteTodoItem = async (data) => {
    try {
      if (confirm("정말로 해당 업무를 제거하시겠습니까?")) {
        const response = await axios.delete(`${BASE_URL}/${data.id}`, data);
        if (response.status === 200) {
          const idx = todoList.value.findIndex((t) => (t.id === data.id));
          todoList.value.splice(idx, 1); 
        } else {
          alert("없는 정보입니다.")
        }
      }
    } catch {
      alert('오류 발생!');
    }
  }

  const addTodoItem = async (data) => {
    try {
      data.finished = false;
      const response = await axios.post(`${BASE_URL}`, data);
      if (response.status === 201) {
        alert("추가 완료!");
        todoList.value.push(response.data);
      } else {
        alert("없는 정보입니다.")
      }
    } catch {
      alert('오류 발생!');
    }
  }

  const toggleTodoItem = async (data) => {
    try {
      data.finished = !data.finished;
      const response = await axios.put(`${BASE_URL}/${data.id}`, data);
      if (response.status === 200) {
        const item = todoList.value.find((t) => (t.id == data.id));
      } else {
        alert("없는 정보입니다.")
      }
    } catch {
      alert('오류 발생!');
    }
  }

  return {
    todoList,
    totalFinishNum,
    totalItemsNum,
    fetchTodoLists,
    deleteTodoItem,
    addTodoItem,
    toggleTodoItem
  }
})
