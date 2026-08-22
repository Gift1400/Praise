<template>
  <header class="praiseHeader">
    <div id="logo-container">
      <h1 id="logo">Praise</h1>
    </div>
    <nav id="navigation">
      <router-link id="link" to="/">Home</router-link>
      <router-link id="link" to="/sermons">Sermon</router-link>
      <router-link id="link" to="/events">Events</router-link>
      <router-link id="link" to="/announcements">Announcements</router-link>
      <router-link id="link" to="/about">About</router-link>
      <router-link id="link" to="/contacts">Contact</router-link>
    </nav>
    <button id="login-signup">Login</button>
  </header>
</template>

<style scoped>
.praiseHeader {
  position: sticky;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  background: #000000c0;
  color: white;
  padding: 1rem;
  filter: blur(2);
  min-height: 9vh;
}

#logo-container {
  align-self: center;
  
}

#logo {
  font-size: 1.5rem;
  align-self: center;
}

.router-link-active {
  font-weight: bold;
  text-decoration: underline;
}

#navigation {
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  align-self: center;
  color: white;

}

#link {
  color: white;
  font-size: 1rem;
  text-decoration: none;
}

#link-active {
  text-decoration: dashed;
}

#login-signup {
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.589);
  filter: blur(4);
  color: black;
  align-self: center;
  height: 30px;
  width: 70px;
  border: none;
}
</style>