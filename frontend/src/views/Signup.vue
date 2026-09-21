```vue
<template>
  <div class="signup-page">

    <div class="signup-container">

      <div class="signup-header">
        <div class="logo">✝</div>

        <h1>Praise Church</h1>

        <p>Create your account</p>
      </div>

      <form @submit.prevent="signup">

        <div class="form-group">
          <label>Full Name</label>

          <input
            v-model="name"
            type="text"
            placeholder="Enter your full name"
            required
          />
        </div>

        <div class="form-group">
          <label>Email Address</label>

          <input
            v-model="email"
            type="email"
            placeholder="Enter your email"
            required
          />
        </div>

        <div class="form-group">
          <label>Password</label>

          <input
            v-model="password"
            type="password"
            placeholder="Create a password"
            required
          />
        </div>

        <div class="form-group">
          <label>Confirm Password</label>

          <input
            v-model="confirmPassword"
            type="password"
            placeholder="Confirm your password"
            required
          />
        </div>

        <button type="submit">
          Create Account
        </button>

      </form>

      <p class="login-text">
        Already have an account?

        <router-link to="/login">
          Sign in
        </router-link>
      </p>

      <router-link to="/" class="home-link">
        ← Back to Home
      </router-link>

    </div>

  </div>
</template>


<script>
export default {
  name: "Signup",

  data() {
    return {
      name: "",
      email: "",
      password: "",
      confirmPassword: ""
    };
  },

  methods: {
    signup() {

      if (this.password !== this.confirmPassword) {
        alert("Passwords do not match.");
        return;
      }

      alert("Account created successfully!");

      this.$router.push("/login");
    }
  }
};
</script>


<style scoped>
.signup-page {
  min-height: 100vh;
  background: #f7f5f0;

  display: flex;
  justify-content: center;
  align-items: center;

  padding: 30px;
}

.signup-container {
  width: 100%;
  max-width: 450px;

  background: white;

  padding: 40px;

  border-radius: 15px;

  box-shadow: 0 10px 35px rgba(0, 0, 0, 0.08);
}

.signup-header {
  text-align: center;
  margin-bottom: 30px;
}

.logo {
  width: 55px;
  height: 55px;

  margin: 0 auto 15px;

  border-radius: 50%;

  background: #80633e;

  color: white;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 25px;
}

.signup-header h1 {
  font-family: Georgia, serif;

  color: #2f281f;

  margin: 0 0 8px;
}

.signup-header p {
  color: #777;

  margin: 0;

  font-size: 14px;
}

.form-group {
  margin-bottom: 18px;
}

.form-group label {
  display: block;

  margin-bottom: 7px;

  font-size: 13px;

  font-weight: 600;

  color: #3d372f;
}

.form-group input {
  width: 100%;

  height: 48px;

  padding: 0 14px;

  border: 1px solid #ddd8cf;

  border-radius: 8px;

  outline: none;

  font-size: 14px;

  box-sizing: border-box;
}

.form-group input:focus {
  border-color: #80633e;

  box-shadow: 0 0 0 3px rgba(128, 99, 62, 0.1);
}

button {
  width: 100%;

  height: 50px;

  margin-top: 5px;

  border: none;

  border-radius: 8px;

  background: #80633e;

  color: white;

  font-size: 14px;

  font-weight: 600;

  cursor: pointer;
}

button:hover {
  background: #674e31;
}

.login-text {
  text-align: center;

  color: #777;

  font-size: 13px;

  margin-top: 25px;
}

.login-text a {
  color: #80633e;

  font-weight: 600;

  text-decoration: none;
}

.home-link {
  display: block;

  text-align: center;

  margin-top: 15px;

  color: #80633e;

  font-size: 13px;

  text-decoration: none;
}
</style>

