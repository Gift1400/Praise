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
            read inspiring messages and grow together in faith.
          </p>
        </div>
      </div>
    </section>

    <!-- Login Section -->
    <section class="login-section">
      <div class="login-card">

        <div class="mobile-logo">
          <div class="cross-icon">✝</div>
          <h2>Praise Church</h2>
        </div>

        <div class="login-header">
          <h1>Welcome back</h1>
          <p>Sign in to continue to your account.</p>
        </div>

        <form @submit.prevent="login">

          <!-- Email -->
          <div class="form-group">
            <label for="email">Email Address</label>

            <div class="input-wrapper">
              <span class="input-icon">✉</span>

              <input
                id="email"
                type="email"
                v-model="email"
                placeholder="Enter your email"
                required
              />
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
              <span class="input-icon">🔒</span>

              <input
                id="password"
                :type="showPassword ? 'text' : 'password'"
                v-model="password"
                placeholder="Enter your password"
                required
              />

              <button
                type="button"
                class="show-password"
                @click="showPassword = !showPassword"
              >
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
          <button type="submit" class="login-button">
            Sign In
          </button>
          <p class="signup-text">
  Don't have an account?
  <router-link to="/signup">Sign up</router-link>
</p>

        </form>

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

<script>
export default {
  name: "Login",

  data() {
    return {
      email: "",
      password: "",
      rememberMe: false,
      showPassword: false,
    };
  },

  methods: {
    login() {
      console.log("Login attempted:", {
        email: this.email,
        password: this.password,
        rememberMe: this.rememberMe,
      });

      // Add your authentication logic here
    },

    forgotPassword() {
      alert("Password reset functionality coming soon.");
    },
  },
};
</script>

<style src="./Style2.css"></style>