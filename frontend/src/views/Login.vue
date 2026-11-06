<template>
  <div class="login-page">

    <!-- Left Welcome Section -->
    <section class="welcome-section">
      <div class="welcome-overlay">
        <div class="church-brand">
          <div class="cross-icon">✝</div>
          <h1>Praise Church</h1>
        </div>

        <div class="welcome-content">
          <p class="small-heading">WELCOME HOME</p>

          <h2>
            Faith that brings us
            <span>together.</span>
          </h2>

          <p>
            Stay connected with Praise Church, discover upcoming events,
            read inspiring messages, and grow together in fellowship.
          </p>
        </div>
      </div>
    </section>

    <!-- Right Login Section -->
    <section class="login-section">
      <div class="login-card">

        <div class="mobile-logo">
          <div class="cross-icon">✝</div>
          <h2>Praise Church</h2>
        </div>

        <div class="login-header">
          <h1>Welcome back</h1>
          <p>Sign in to continue to your church portal.</p>
        </div>

        <!-- User Type Toggle -->
        <div class="user-type-section">
          <label class="user-type-label">Select Account Type</label>
          <div class="user-type-toggle">
            <button
              type="button"
              class="type-option"
              :class="{ active: userType === 'MEMBER' }"
              @click="userType = 'MEMBER'"
            >
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
              </svg>
              Member
            </button>
            <button
              type="button"
              class="type-option"
              :class="{ active: userType === 'LEADER' }"
              @click="userType = 'LEADER'"
            >
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M2 4l3 12h14l3-12-6 7-4-7-4 7-6-7z"></path>
                <circle cx="12" cy="19" r="1.5"></circle>
              </svg>
              Leader
            </button>
          </div>
        </div>

        <form @submit.prevent="login">

          <!-- Email -->
          <div class="form-group">
            <label for="email">Email Address</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                  stroke-linecap="round" stroke-linejoin="round">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path>
                  <polyline points="22,6 12,13 2,6"></polyline>
                </svg>
              </span>
              <input id="email" type="email" v-model="email" placeholder="Enter your email" required />
            </div>
          </div>

          <!-- Password -->
          <div class="form-group">
            <div class="password-label">
              <label for="password">Password</label>
              <a href="#" @click.prevent="forgotPassword">
                Forgot password?
              </a>
            </div>

            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                  stroke-linecap="round" stroke-linejoin="round">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                </svg>
              </span>

              <input
                id="password"
                :type="showPassword ? 'text' : 'password'"
                v-model="password"
                placeholder="Enter your password"
                required
              />

              <button type="button" class="show-password" @click="showPassword = !showPassword">
                {{ showPassword ? "Hide" : "Show" }}
              </button>
            </div>
          </div>

          <!-- Remember Me -->
          <div class="remember-row">
            <label class="remember">
              <input type="checkbox" v-model="rememberMe" />
              <span>Remember me</span>
            </label>
          </div>

          <!-- Login Button -->
          <button type="submit" class="login-button" :disabled="isLoading">
            {{ isLoading ? "Signing In..." : ("Sign In as " + (userType === 'LEADER' ? 'Leader' : 'Member')) }}
          </button>

        </form>

        <!-- Error Message Below Form -->
        <div v-if="errorMessage" class="error-message">
          {{ errorMessage }}
        </div>

        <!-- Sign Up -->
        <div class="signup-section">
          <p>
            Don't have an account?
            <router-link to="/signup">
              Sign up
            </router-link>
          </p>
        </div>

        <div class="divider">
          <span>or</span>
        </div>

        <p class="guest-text">
          You can also explore Praise Church as a guest.
        </p>

        <router-link to="/" class="home-link">
          ← Back to Home
        </router-link>

      </div>
    </section>

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const userType = ref('MEMBER')
const email = ref('')
const password = ref('')
const rememberMe = ref(false)
const showPassword = ref(false)
const errorMessage = ref('')
const isLoading = ref(false)

const login = async () => {
  errorMessage.value = ''
  isLoading.value = true

  try {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email.value.trim(),
        password: password.value,
        userType: userType.value,
      }),
    })

    let data = null
    const contentType = response.headers.get('content-type')
    if (contentType && contentType.includes('application/json')) {
      data = await response.json()
    } else {
      const text = await response.text()
      if (text) {
        data = { message: text }
      }
    }

    if (!response.ok) {
      const msg = data?.message || data?.error || 'Invalid email or password.'
      throw new Error(msg)
    }

    const token = data?.token || data?.accessToken || data?.jwt || ''
    const user = {
      firstName: data?.firstName || (email.value ? email.value.split('@')[0] : 'Member'),
      lastName: data?.lastName || '',
      email: data?.email || email.value.trim(),
      role: data?.role || userType.value,
    }

    
    const storage = rememberMe.value ? localStorage : sessionStorage
    const otherStorage = rememberMe.value ? sessionStorage : localStorage

    otherStorage.removeItem('token')
    otherStorage.removeItem('user')

    if (token) {
      storage.setItem('token', token)
    }
    storage.setItem('user', JSON.stringify(user))

    router.push('/')
  } catch (err) {
    errorMessage.value = err.message || 'An error occurred during login. Please try again.'
  } finally {
    isLoading.value = false
  }
}

const forgotPassword = () => {
  alert('Password reset functionality coming soon. Please contact your church administrator.')
}
</script>

<style src="../styles/login.css"></style>
<style src="../styles/user-types.css"></style>

<style scoped>
.error-message {
  margin-top: 18px;
  padding: 12px 14px;
  background-color: #fdf2f2;
  color: #b91c1c;
  border: 1px solid #fecaca;
  border-radius: 9px;
  font-size: 13px;
  text-align: center;
}

.login-button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
  transform: none;
}

.input-icon svg {
  display: block;
}
</style>
