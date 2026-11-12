<template>
  <header class="praiseHeader">
    <div id="logo-container">
      <router-link to="/" class="logo-link">
        <h1 id="logo">Pra✝se</h1>
      </router-link>
    </div>

    <!-- Mobile Hamburger Button -->
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

    <!-- Navigation Links Container -->
    <nav class="navigation" :class="{ 'menu-open': isMenuOpen }">
      <!-- Clicking any link automatically closes the mobile dropdown -->
      <router-link class="link" to="/" @click="isMenuOpen = false">Home</router-link>

      <!-- Main ministry pages: ONLY shown when logged in -->
      <template v-if="currentUser">
        <router-link class="link" to="/sermons" @click="isMenuOpen = false">Sermons</router-link>
        <router-link class="link" to="/events" @click="isMenuOpen = false">Events</router-link>
        <router-link class="link" to="/announcements" @click="isMenuOpen = false">Announcements</router-link>
        <router-link class="link" to="/prayer" @click="isMenuOpen = false">Prayer</router-link>
        <router-link class="link" to="/verses" @click="isMenuOpen = false">Verses</router-link>
        <router-link class="link" to="/outreach" @click="isMenuOpen = false">Outreach</router-link>
        
      </template>

      <!-- Simple pages (Always visible) -->
      <router-link class="link" to="/about" @click="isMenuOpen = false">About</router-link>
      <router-link class="link" to="/contacts" @click="isMenuOpen = false">Contact</router-link>

      <!-- Member Profile / Leader Dashboard Link (Only when logged in) -->
      <router-link
        v-if="currentUser?.role === 'LEADER' || currentUser?.role === 'ADMIN'"
        class="link dashboard-link"
        to="/dashboard"
        @click="isMenuOpen = false"
      >
        Dashboard
      </router-link>
      <router-link
        v-else-if="currentUser"
        class="link profile-nav-link"
        to="/profile"
        @click="isMenuOpen = false"
      >
      
      </router-link>

      <!-- Mobile Auth State (Rendered inside mobile menu) -->
      <div v-if="currentUser" class="mobile-auth-wrapper mobile-only">
        <router-link
          :to="currentUser.role === 'LEADER' ? '/dashboard' : '/profile'"
          class="mobile-profile-info"
          @click="isMenuOpen = false"
        >
          <div class="profile-vector-avatar">
            <svg width="22" height="22" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
              <circle cx="12" cy="7" r="4"></circle>
            </svg>
          </div>
          <span class="user-name-text">
            {{ currentUser.firstName || 'User' }} ({{ currentUser.role === 'LEADER' ? 'Dashboard' : 'My Profile' }})
          </span>
          <span v-if="currentUser.role === 'LEADER'" class="leader-badge-pill">Leader</span>
        </router-link>
        <button class="logout-btn" @click="logout">Logout</button>
      </div>

      <!-- Mobile Login -->
      <router-link v-else class="login-signup mobile-only" to="/login" @click="isMenuOpen = false">login</router-link>
    </nav>

    <!-- Desktop Auth Container (Right Slot) -->
    <div class="auth-slot desktop-only">
      <!-- When logged in: Profile Vector + First Name + Leader badge + Quick Logout -->
      <div v-if="currentUser" class="user-profile-badge">
        <router-link
          :to="currentUser.role === 'LEADER' ? '/dashboard' : '/profile'"
          class="profile-badge-link"
          :title="currentUser.role === 'LEADER' ? 'Go to Leadership Dashboard' : 'Open My Member Profile'"
        >
          <div class="profile-vector-avatar">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
              <circle cx="12" cy="7" r="4"></circle>
            </svg>
          </div>
          <span class="user-name-text">{{ currentUser.firstName || 'User' }}</span>
          <span v-if="currentUser.role === 'LEADER'" class="leader-badge-pill">Leader</span>
        </router-link>
        <button class="logout-icon-btn" @click="logout" title="Sign out" aria-label="Sign out">
          <svg width="15" height="15" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
            <polyline points="16 17 21 12 16 7"></polyline>
            <line x1="21" y1="12" x2="9" y2="12"></line>
          </svg>
        </button>
      </div>

      <!-- When logged out: Login Button -->
      <router-link v-else class="login-signup" to="/login">login</router-link>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import { logout as authLogout } from '../util/auth'

const route = useRoute()
const isMenuOpen = ref(false)
const currentUser = ref(null)

const checkAuth = () => {
  const raw = localStorage.getItem('user') || sessionStorage.getItem('user')
  if (raw) {
    try {
      const parsed = JSON.parse(raw)
      currentUser.value = typeof parsed === 'object' && parsed !== null ? parsed : { firstName: raw }
    } catch {
      currentUser.value = { firstName: raw }
    }
  } else {
    currentUser.value = null
  }
}

const logout = () => {
  authLogout()
  currentUser.value = null
  isMenuOpen.value = false
  window.location.reload()
}

onMounted(() => {
  checkAuth()
})

watch(
  () => route.path,
  () => {
    checkAuth()
    isMenuOpen.value = false
  }
)
</script>

<style scoped>
.praiseHeader {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  display: grid;
  grid-template-columns: auto 1fr auto;
  align-items: center;
  background: rgb(0 0 0 / 87%);
  color: white;
  backdrop-filter: blur(6px);
  min-height: 12vh;
  padding: 0 1.8rem;
  z-index: 99;
  -webkit-backdrop-filter: blur(6px);
  animation: blur-header linear both;
  animation-timeline: scroll(root);
  animation-range: 0px 200px;
  font-family: 'Arial Narrow Bold', sans-serif;
  box-sizing: border-box;
}

@keyframes blur-header {
  to {
    background-color: rgb(0 0 0 / 55%);
    backdrop-filter: blur(8px);
    -webkit-backdrop-filter: blur(12px);
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.08);
  }
}

#logo-container {
  grid-column: 1;
  justify-self: start;
  display: flex;
  align-items: center;
}

.logo-link {
  text-decoration: none;
  color: inherit;
}

#logo {
  font-size: 1.5rem;
  color: white;
  user-select: none;
  -webkit-user-select: none;
  margin: 0;
}

.navigation {
  grid-column: 2;
  justify-self: center;
  display: flex;
  flex-direction: row;
  gap: 1.15rem;
  align-items: center;
}

.navigation .link {
  color: white;
  font-size: 0.95rem;
  text-decoration: none;
  transition: opacity 0.2s ease;
  white-space: nowrap;
}

.navigation .link:hover {
  opacity: 0.8;
}

.dashboard-link {
  color: #dfba73 !important;
  font-weight: 600;
}

.router-link-active {
  backdrop-filter: blur(2px);
  border-bottom: 2px solid #9b6d00;
  padding-bottom: 2px;
  border-radius: 3px;
}

.auth-slot {
  grid-column: 3;
  justify-self: end;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.login-signup {
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 20px;
  background-color: #9b6d00;
  color: rgb(255, 254, 254);
  height: 34px;
  width: 5.6rem;
  text-decoration: none;
  font-size: 0.95rem;
  font-weight: 600;
  transition: transform 0.2s, background-color 0.2s;
}

.login-signup:hover {
  background-color: #b08d57;
}

.login-signup:active {
  transform: scale(0.95);
}

/* User profile badge */
.user-profile-badge {
  display: flex;
  align-items: center;
  gap: 8px;
  background: rgba(255, 255, 255, 0.08);
  border: 1px solid rgba(255, 255, 255, 0.16);
  padding: 4px 12px 4px 6px;
  border-radius: 24px;
  backdrop-filter: blur(4px);
  transition: all 0.2s ease;
}

.user-profile-badge:hover {
  background: rgba(255, 255, 255, 0.14);
}

.profile-vector-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background: #9b6d00;
  color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.25);
  flex-shrink: 0;
}

.profile-vector-avatar svg {
  display: block;
}

.user-name-text {
  font-size: 0.9rem;
  font-weight: 600;
  color: #ffffff;
  letter-spacing: 0.2px;
}

.leader-badge-pill {
  font-size: 0.68rem;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  background: #9b6d00;
  color: #fff;
  padding: 2px 6px;
  border-radius: 8px;
  font-weight: bold;
}

.logout-icon-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: none;
  background: rgba(255, 255, 255, 0.12);
  color: #f1ede6;
  cursor: pointer;
  margin-left: 2px;
  transition: background-color 0.2s, color 0.2s;
}

.logout-icon-btn:hover {
  background: rgba(239, 68, 68, 0.25);
  color: #fca5a5;
}

.mobile-only {
  display: none;
}

.desktop-only {
  display: flex;
}

.menu-toggle {
  display: none;
}

/* Mobile dropdown styles */
@media (max-width: 1160px) {
  .praiseHeader {
    display: flex;
    justify-content: space-between;
    padding: 0 1.5rem;
  }

  .desktop-only {
    display: none !important;
  }

  .mobile-only {
    display: flex;
  }

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
    border-radius: 5px;
    transition: all 0.3s ease-in-out;
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
    background-color: rgba(0, 0, 0, 0.95);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 1.4rem;
    transition: right 0.4s cubic-bezier(0.77, 0, 0.175, 1);
    box-shadow: -5px 0 15px rgba(0, 0, 0, 0.5);
    overflow-y: auto;
    padding: 40px 0;
  }

  .navigation.menu-open {
    right: 0;
  }

  .navigation .link {
    font-size: 1.15rem;
  }

  .mobile-auth-wrapper {
    flex-direction: column;
    align-items: center;
    gap: 1rem;
    margin-top: 1rem;
  }

  .mobile-profile-info {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  .logout-btn {
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 20px;
    background-color: #9b6d00;
    color: rgb(255, 254, 254);
    height: 36px;
    padding: 0 1.5rem;
    border: none;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: transform 0.2s, background-color 0.2s;
    font-family: inherit;
  }
}

.profile-badge-link {
  display: flex;
  align-items: center;
  gap: 8px;
  text-decoration: none;
  color: inherit;
}

.profile-badge-link:hover .user-name-text {
  color: #dfba73;
}

</style>
