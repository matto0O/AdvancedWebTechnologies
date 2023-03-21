AOS.init({
  duration: 1200,
});

//pie
var ctxP = document.getElementById("chart").getContext("2d");
var barChart = new Chart(ctxP, {
  type: "bar",
  data: {
    labels: ["Spain", "USA", "Poland", "Asia", "Others"],
    datasets: [
      {
        label: "",
        data: [250000, 400000, 600000, 170000, 340000],
        backgroundColor: [
          "#F7464A",
          "#46BFBD",
          "#FDB45C",
          "#949FB1",
          "#4D5360",
        ],
        hoverBackgroundColor: [
          "#FF5A5E",
          "#5AD3D1",
          "#FFC870",
          "#A8B3C5",
          "#616774",
        ],
      },
    ],
  },
  options: {
    responsive: true,
    legend: {
      display: false,
    },
  },
});
