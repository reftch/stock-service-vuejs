<template>
  <div>

    <div class=chart-area>
      <div class="form-dropdown">
        <c-dropdown
          :disabled="!dates || dates.length === 0"
          v-model.lazy="date"
          :options="dates"
          @change="setDate"
        />
      </div>

      <canvas id="id-chart" ></canvas>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, computed, ref, watch } from "vue";
import { Chart } from "chart.js";
import { useStore } from "vuex";
import { PriceState } from '../../model';

export default defineComponent({
  name: "Chart",
  setup() {
    let chart = ref();
    const store = useStore();

    const dailyData = computed(() => store.getters["companies/getDailyPrice"]);

    const date = ref("Daily prices...");
    const dates = computed(() => {
      const prices = store.getters["companies/getPrices"];
      return prices.map((p: PriceState) => p.date);
    });

    const labels = ["Open", "High", "Low", "Close"];
    const data = {
      labels: labels,
      datasets: [
        {
          label: "Daily Prices",
          data: [],
          fill: true,
          borderColor: "#0399f7",
          // backgroundColor: "#f2f6fc",
          lineTension: 0.1,
          borderWidth: 3,
        },
      ],
    };

    const config = {
      type: "line",
      data: data,
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: "top",
          },
          title: {
            display: true,
            text: "Chart.js Line Chart",
          },
        },
      },
    };

    const setDate = async () => {
      await store.dispatch("companies/setDailyPrice", date.value);

      // eslint-disable-next-line
      chart.value.data.datasets.forEach(async (dataset: any) => {
        if (dataset.data && dataset.data.length > 0) {
          dataset.data.length = 0;
        }
        // eslint-disable-next-line
        await dailyData.value.forEach((d: any) => {
          dataset.data.push(d);
        });
      });
      chart.value.update();
    }

    onMounted(() => {
      const ctx = document.getElementById("id-chart");
      if (ctx) {
        chart.value = new Chart(ctx, config);
      }
    });

    watch((dates), (newDates) => {
      date.value = newDates.length > 0 ? newDates[0] : 'Daily prices...';
      setDate();        
    })

    return {
      date,
      dates,
      setDate,
    };
  },
});
</script>
