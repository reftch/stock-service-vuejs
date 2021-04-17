<template>
  <div class="input-field">
    <input
      type="text"
      v-focus
      :value="getValue"
      placeholder="Select a Company..."
      @keyup="keyUp"
    />
    <div v-show="showOptions" class="input-field-options">
        <div v-for="item in options" :key="item.symbol" 
          class="input-field-item"
          @click="selectItem(item)">
            <span class="input-field-symbol">{{ item.symbol }}</span> ({{item.name}})
        </div>
    </div>

  </div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import { MatchesState } from "@/model";
import { useStore } from "vuex";

export default defineComponent({
  name: "InputField",
  model: {
    prop: "modelValue",
  },
  props: {
    modelValue: { default: "" },
  },
  setup(props, context) {
    const store = useStore();
    const showOptions = ref(false);
    const isTimeout = ref(false);
    const options = computed(() => store.getters["companies/getItems"]);

    const selectItem = async (item: MatchesState) => {
      context.emit('update:modelValue', item.symbol);
      showOptions.value = false;
      await store.dispatch(`companies/setCompany`, item);
      store.dispatch(`companies/reset`);
    }

    // eslint-disable-next-line 
    const keyUp = async (event: any) => {
      context.emit('update:modelValue', event.target.value);

      showOptions.value = event.target.value !== '';
      if (event.key === 'Escape') {
        showOptions.value = false;
        // store.dispatch(`companies/reset`);
      } else if (event.key === 'Enter') {
        showOptions.value = false;
        selectItem({ symbol: event.target.value })
      }

      if (event.target.value === '') {
         store.dispatch(`companies/reset`);
      }

      if (showOptions.value) {
        if (!isTimeout.value) {
          isTimeout.value = true;
          setTimeout(() => {
            if (event.target.value !== '') {
              store.dispatch(`companies/fetch`, event.target.value);
            }
            isTimeout.value = false;
          }, 500);
        }
      }
    }

    return {
      showOptions,
      options,
      keyUp,
      selectItem,
      getValue: computed(() => props.modelValue),
    }
  }
});
</script>
