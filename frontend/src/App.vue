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
  margin: 0 !important;
  padding: 0 !important;
  box-sizing: border-box;
}

/* 2. Full-Screen Container Setup */
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  /* Forces the box to exactly match the screen height */
  width: 100%;
}

/* 3. Reusable Content Engine */
.main-content {
  flex: 1;
  /* Grows vertically to pin the footer to the floor */
  padding: 2rem;
  /* Keeps text from bunching up against the glass edge */
  background-color: #f9f9f9;
}
</style>
