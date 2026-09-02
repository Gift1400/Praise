<template>
  <header class="praiseHeader">
    <div id="logo-container">
      <h1 id="logo">Praise</h1>
    </div>
    <nav class="navigation">
      <router-link class="link" to="/">Home</router-link>
      <router-link class="link" to="/sermons">Sermon</router-link>
      <router-link class="link" to="/events">Events</router-link>
      <router-link class="link" to="/announcements">Announcements</router-link>
      <router-link class="link" to="/about">About</router-link>
      <router-link class="link" to="/contacts">Contact</router-link>
    </nav>
    <button id="login-signup">Login</button>
  </header>
</template>

<style scoped>
.praiseHeader {
  position: sticky;
  top: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background: rgb(0 0 0 / 87%);
  color: white;
  backdrop-filter: blur(5px);
  min-height: 10vh;
  z-index: 9;
  -webkit-backdrop-filter: blur(5px);
  animation: blur-header linear both;
  animation-timeline: scroll(root);
  animation-range: 0px 200px;
  font-family:'Arial Narrow Bold', sans-serif;
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
  align-self: center;

}

#logo {
  font-size: 1.5rem;
  justify-content: left;
  color: white;
  z-index: 10;
}

.router-link-active {
  border:0.1px solid white;
  border-radius:2px;
  background-color:rgba(0, 0, 255, 0.46);
  
}

.navigation {
  display: flex;
  flex-direction: row;
  gap: 10px;
  align-items: center;
  align-self: center;
  color: white;

  .link {
    position:relative;
    color: white;
    font-size: 1rem;
    text-decoration:none;
  }

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