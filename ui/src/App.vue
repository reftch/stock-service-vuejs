<template>
  <c-header></c-header>

  <!-- <div v-for="item in items" :key="item.symbol">
    {{ item.symbol }}: ({{ item.name }})
  </div> -->

</template>

<script lang="ts">
import { defineComponent, computed } from 'vue';
import { useStore } from 'vuex';

export default defineComponent({
  name: 'App',
  setup() {
    const store = useStore();
    // const items = computed(() => store.getters["companies/getItems"]);

    return {
      // items,
    }
  }
});
</script>

<style lang="scss">
@import './styles/index';
</style>
