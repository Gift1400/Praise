<template>
  <div class="signup-page">
    <div class="signup-container">

     <div class="signup-header">
        <div class="logo-cross">✝</div>
        <h1>Praise Church</h1>
        <p>Create your account to connect and fellowship with our church family.</p>
      </div>

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

      <form class="signup-form" @submit.prevent="signup">

        <div class="form-grid-row">
          <div class="form-group">
            <label for="firstName">First Name</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </svg>
              </span>
              <input
                id="firstName"
                v-model="firstName"
                type="text"
                placeholder="First name"
                required
              />
            </div>
          </div>

          <div class="form-group">
            <label for="lastName">Last Name</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                  <circle cx="12" cy="7" r="4"></circle>
                </svg>
              </span>
              <input
                id="lastName"
                v-model="lastName"
                type="text"
                placeholder="Last name"
                required
              />
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="email">Email Address</label>
          <div class="input-wrapper">
            <span class="input-icon">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path>
                <polyline points="22,6 12,13 2,6"></polyline>
              </svg>
            </span>
            <input
              id="email"
              v-model="email"
              type="email"
              placeholder="e.g. yourname@example.com"
              required
            />
          </div>
        </div>

        <div v-if="userType === 'LEADER'" class="leader-code-box">
          <div class="form-group">
            <label for="accessCode">Leader Access Code</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                </svg>
              </span>
              <input
                id="accessCode"
                v-model="accessCode"
                type="password"
                placeholder="Enter leader authorization code"
                required
              />
            </div>
            <p class="leader-code-hint">
              🔒 Required to activate church leader administrative permissions.
            </p>
          </div>
        </div>

        <div class="form-grid-row">
          <div class="form-group">
            <label for="password">Password</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                </svg>
              </span>
              <input
                id="password"
                :type="showPassword ? 'text' : 'password'"
                v-model="password"
                placeholder="At least 6 characters"
                required
              />
            </div>
          </div>

          <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <div class="input-wrapper">
              <span class="input-icon">
                <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                  <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
                </svg>
              </span>
              <input
                id="confirmPassword"
                :type="showPassword ? 'text' : 'password'"
                v-model="confirmPassword"
                placeholder="Confirm password"
                required
              />
            </div>
          </div>
        </div>

      
        <div class="password-toggle-row">
          <label class="toggle-label">
            <input type="checkbox" v-model="showPassword" />
            <span>Show passwords</span>
          </label>
        </div>

         <div v-if="errorMessage" class="error-message">
          {{ errorMessage }}
        </div>

       <button type="submit" class="submit-button" :disabled="isLoading">
          <span v-if="isLoading">Creating Account...</span>
          <span v-else>
            Create {{ userType === 'LEADER' ? 'Leader' : 'Member' }} Account
          </span>
        </button>

      </form>

  
      <div class="login-redirect">
        <p>
          Already have an account?
          <router-link to="/login" class="login-link">
            Sign in
          </router-link>
        </p>
      </div>

      
      <div class="home-redirect">
        <router-link to="/" class="home-link">
          ← Back to Home
        </router-link>
      </div>

    </div>
  </div>
</template>


<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const userType = ref('MEMBER')
const firstName = ref('')
const lastName = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const accessCode = ref('')
const showPassword = ref(false)
const errorMessage = ref('')
const isLoading = ref(false)

const signup = async () => {
  errorMessage.value = ''

  if (password.value !== confirmPassword.value) {
    errorMessage.value = 'Passwords do not match.'
    return
  }

  if (password.value.length < 6) {
    errorMessage.value = 'Password must be at least 6 characters long.'
    return
  }

  if (userType.value === 'LEADER' && !accessCode.value.trim()) {
    errorMessage.value = 'Leader access code is required.'
    return
  }

  isLoading.value = true

  try {
    const response = await fetch('/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email.value.trim(),
        password: password.value,
        firstName: firstName.value.trim(),
        lastName: lastName.value.trim(),
        userType: userType.value,
        accessCode: accessCode.value.trim(),
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
      const msg = data?.message || data?.error || 'Registration failed. Please check your information.'
      throw new Error(msg)
    }

    const token = data?.token || data?.accessToken || data?.jwt || ''
    if (token) {
      localStorage.setItem('token', token)
    }

    const user = {
      firstName: data?.firstName || firstName.value.trim(),
      lastName: data?.lastName || lastName.value.trim(),
      email: data?.email || email.value.trim(),
      role: data?.role || userType.value,
    }
    localStorage.setItem('user', JSON.stringify(user))

    router.push('/')
  } catch (err) {
    errorMessage.value = err.message || 'An error occurred during registration.'
  } finally {
    isLoading.value = false
  }
}
</script>


<style scoped>
.signup-page {
  min-height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 24px;
  background:
    linear-gradient(rgba(24, 18, 12, 0.72), rgba(24, 18, 12, 0.82)),
    linear-gradient(135deg, #8b6f47 0%, #3d3021 100%);
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  box-sizing: border-box;
}

.signup-container {
  width: 100%;
  max-width: 620px;
  background: #ffffff;
  padding: 44px 48px;
  border-radius: 20px;
  box-shadow: 0 16px 45px rgba(0, 0, 0, 0.18);
  box-sizing: border-box;
}

.signup-header {
  text-align: center;
  margin-bottom: 28px;
}

.logo-cross {
  width: 50px;
  height: 50px;
  margin: 0 auto 14px;
  border-radius: 50%;
  background: #80633e;
  color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  box-shadow: 0 4px 12px rgba(128, 99, 62, 0.35);
}

.signup-header h1 {
  font-family: Georgia, serif;
  font-size: 32px;
  font-weight: 600;
  color: #2f281f;
  margin: 0 0 8px;
}

.signup-header p {
  color: #77716a;
  font-size: 14px;
  margin: 0;
  line-height: 1.5;
}

.signup-form {
  display: flex;
  flex-direction: column;
}

.form-grid-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 8px;
}

.input-wrapper {
  position: relative;
  display: flex;
  align-items: center;
}

.input-icon {
  position: absolute;
  left: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #9c9183;
  pointer-events: none;
}

.input-wrapper input {
  width: 100%;
  height: 48px;
  border: 1px solid #ddd8cf;
  border-radius: 10px;
  padding: 0 16px 0 44px;
  font-size: 14px;
  outline: none;
  background: #faf9f6;
  color: #2f281f;
  transition: all 0.2s ease;
  font-family: inherit;
  box-sizing: border-box;
}

.input-wrapper input:focus {
  background: #ffffff;
  border-color: #80633e;
  box-shadow: 0 0 0 3px rgba(128, 99, 62, 0.15);
}

.password-toggle-row {
  display: flex;
  align-items: center;
  margin-bottom: 22px;
}

.toggle-label {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 13px;
  color: #6b5a40;
  cursor: pointer;
}

.toggle-label input[type='checkbox'] {
  accent-color: #80633e;
  cursor: pointer;
  width: 15px;
  height: 15px;
}

.error-message {
  padding: 12px 14px;
  background-color: #fdf2f2;
  color: #b91c1c;
  border: 1px solid #fecaca;
  border-radius: 10px;
  font-size: 13px;
  text-align: center;
  margin-bottom: 18px;
}

.submit-button {
  width: 100%;
  height: 50px;
  border: none;
  border-radius: 10px;
  background-color: #80633e;
  color: #ffffff;
  font-size: 15px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
  font-family: inherit;
  box-shadow: 0 4px 14px rgba(128, 99, 62, 0.35);
}

.submit-button:hover:not(:disabled) {
  background-color: #6e5433;
  transform: translateY(-1px);
}

.submit-button:active:not(:disabled) {
  transform: translateY(0);
}

.submit-button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
  transform: none;
}

.login-redirect {
  text-align: center;
  margin-top: 22px;
  font-size: 14px;
  color: #666;
}

.login-link {
  color: #80633e;
  font-weight: 600;
  text-decoration: none;
  margin-left: 5px;
}

.login-link:hover {
  text-decoration: underline;
}

.home-redirect {
  text-align: center;
  margin-top: 14px;
}

.home-link {
  font-size: 13px;
  color: #8c8273;
  text-decoration: none;
  transition: color 0.2s;
}

.home-link:hover {
  color: #2f281f;
}


@media (max-width: 640px) {
  .signup-container {
    padding: 30px 22px;
  }

  .form-grid-row {
    grid-template-columns: 1fr;
    gap: 0;
  }

  .signup-header h1 {
    font-size: 26px;
  }
}
</style>


