<template>
  <header class="praiseHeader">
    <div id="logo-container">
      <h1 id="logo">Pra✝se</h1>
    </div>

    <!-- 🍔 Mobile Hamburger Button -->
    <button 
      class="menu-toggle" 
      @click="isMenuOpen = !isMenuOpen" 
      :class="{ 'is-active': isMenuOpen }"
      aria-label="Toggle navigation menu"
    >
      <span class="bar"></span>
      <span class="bar"></span>
      <span class="bar"></span>
    </button>

    <!-- 🧭 Navigation Links Container -->
    <nav class="navigation" :class="{ 'menu-open': isMenuOpen }">
      <!-- Clicking any link automatically closes the mobile dropdown -->
      <router-link class="link" to="/" @click="isMenuOpen = false">Home</router-link>
      <router-link class="link" to="/sermons" @click="isMenuOpen = false">Sermon</router-link>
      <router-link class="link" to="/events" @click="isMenuOpen = false">Events</router-link>
      <router-link class="link" to="/announcements" @click="isMenuOpen = false">Announcements</router-link>
      <router-link class="link" to="/about" @click="isMenuOpen = false">About</router-link>
      <router-link class="link" to="/contacts" @click="isMenuOpen = false">Contact</router-link>
      
      <!-- Login embedded natively inside mobile menu tracking list -->
      <router-link class="login-signup mobile-only" to="/login" @click="isMenuOpen = false">login</router-link>
    </nav>

    <!-- Desktop-only Login Button -->
    <router-link class="login-signup desktop-only" to="/login">login</router-link>
  </header>
</template>

<script setup>
import { ref } from 'vue'

const isMenuOpen = ref(false)
</script>

<style scoped>
.praiseHeader {
  position:fixed; 
  top: 0;
  left:0;
  width:100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background: rgb(0 0 0 / 87%);
  color: white;
  backdrop-filter: blur(5px);
  min-height: 12vh;
  padding: 0 2rem;
  z-index: 99;
  -webkit-backdrop-filter: blur(5px);
  animation: blur-header linear both;
  animation-timeline: scroll(root);
  animation-range: 0px 200px;
  font-family: 'Arial Narrow Bold', sans-serif;
}

@keyframes blur-header {
  to {
    background-color: rgb(0 0 0 / 50%);
    backdrop-filter: blur(7px);
    -webkit-backdrop-filter: blur(12px);
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.05);
  }
}

#logo-container {
  display: flex;
  align-items: center;
}

#logo {
  font-size: 1.5rem;
  color: white;
  user-select: none;
  -webkit-user-select: none;
}

.navigation {
  display: flex;
  flex-direction: row;
  gap: 1.5rem;
  align-items: center;
}

.navigation .link {
  color: white;
  font-size: 1rem;
  text-decoration: none;
  transition: opacity 0.2s ease;
}

.navigation .link:hover {
  opacity: 0.8;
}

.router-link-active {
  backdrop-filter: blur(2px);
  border-bottom: 2px solid #9b6d00;
  padding-bottom: 2px;
}

.login-signup {
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 20px;
  background-color: #9b6d00;
  color: rgb(255, 254, 254);
  height: 34px;
  width: 6rem;
  text-decoration: none;
  font-size: 1rem;
  font-weight: 600;
  transition: transform 0.2s;
}

.login-signup:active {
  transform: scale(0.95);
}


.mobile-only { display: none; }
.desktop-only { display: flex; }
.menu-toggle { display: none; }

@media (max-width: 850px) {
  .praiseHeader {
    padding: 0 1.5rem;
  }

  .desktop-only { display: none; }
  .mobile-only { display: flex; }


  .menu-toggle {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 25px;
    height: 18px;
    background: transparent;
    border: none;
    cursor: pointer;
    z-index: 100;
  }

  .menu-toggle .bar {
    width: 100%;
    height: 3px;
    background-color: white;
    border-radius: 2px;
    transition: all 0.3s ease;
  }

  .menu-toggle.is-active .bar:nth-child(1) {
    transform: translateY(7.5px) rotate(45deg);
  }
  .menu-toggle.is-active .bar:nth-child(2) {
    opacity: 0;
  }
  .menu-toggle.is-active .bar:nth-child(3) {
    transform: translateY(-7.5px) rotate(-45deg);
  }


  .navigation {
    position: fixed;
    top: 0;
    right: -100%; 
    width: 75%;
    height: 100vh;
    background: rgba(0, 0, 0, 0.95);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    flex-direction: column;
    justify-content: center;
    gap: 2rem;
    transition: right 0.3s ease-in-out;
    box-shadow: -5px 0 15px rgba(0, 0, 0, 0.5);
  }

  .navigation.menu-open {
    right: 0;
  }

  .navigation .link {
    font-size: 1.25rem; 
    width: 100%;
    text-align: center;
    padding: 0.5rem 0;
  }
  
  .router-link-active {
    border-bottom: none;
    color: #9b6d00;
  }
}
</style>
