<template>
  <c-header></c-header>

  <div v-if="company" class="title">
    <h2>{{ company.name }} </h2>
  </div>

  <div v-if="company" class="content">
    <div class="content-part">
      <c-chart></c-chart>
    </div>
    <div class="content-part">
      <c-table
        :item="company"
        :columns="columns"
      />
    </div>
  </div>

</template>

<script lang="ts">
import { defineComponent, computed } from 'vue';
import { useStore } from "vuex";

export default defineComponent({
  name: 'App',
  setup() {
    const store = useStore();
    const company = computed(() => store.getters["companies/getCompany"]);
    const columns = [
      { id: 'Parameter' },
      { id: 'Value' },
    ]
    return {
      company,
      columns
    }
  }
});
</script>

<style lang="scss">
@import './styles/index';
</style>
