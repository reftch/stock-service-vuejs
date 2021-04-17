<template>
  <div class="chart-area">
    <div class="table">
      <div class="table-heading">
        <div
          v-for="column in columns"
          :key="column"
          class="table-cell table-header"
        >
          <div class="table-heading-title">
            {{ column.id }}
          </div>
        </div>
      </div>
      <div
        v-for="(value, propertyName) in item"
        :key="propertyName"
        class="table-row"
      >
        <div class="table-cell">
          <div class="table-cell-parameter">{{ propertyName }}</div>
        </div>
        <div class="table-cell">
          <div>{{ value }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    item: { type: Object },
    columns: { type: Array },
  },
  setup() {
    return {};
  },
});
</script>

