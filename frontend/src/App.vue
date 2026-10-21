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
*::before,
*::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
 
html,
body {
  margin: 0;
  padding: 0;
  scroll-behavior: smooth;
  scrollbar-width: none;
  background-color: #f9f9f9;
}
 
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100dvh;
  width: 100%;
}

 
.main-content {
  flex: 1; 
  width: 100%;
}
</style>
