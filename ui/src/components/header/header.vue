<template>
  <div class="header">
    <div class="header-input">
      <c-input-field v-model="keywords"></c-input-field>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
// import { useStore } from "vuex";

export default defineComponent({
  name: "Header",
  setup() {
    const keywords = ref('');
    // const store = useStore();

    // const keyUp = () => {
    //   // console.log(keywords.value)

    //   store.dispatch(`companies/fetch`, keywords.value);
    // }

    return {
      keywords,
      // options,
      // keyUp,
    }
  }
});
</script>
