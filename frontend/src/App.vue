<script setup>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
</script>

<template>
  <div class="app-container">
    <Header />

    <main class="main-content">
      <router-view />
    </main>


    <Footer />
  </div>
</template>

<style>
*,
html,
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  width: 100%;
}

.main-content {
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
}
</style>
