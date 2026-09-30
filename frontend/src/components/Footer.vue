<template>
  <footer class="praiseFooter">
    <article id="components">
      <h3>Praise</h3>
      <nav id="footer-nav">
        <router-link id="link" to="/">Home</router-link>
        <router-link id="link" to="/sermons">Sermon</router-link>
        <router-link id="link" to="/events">Events</router-link>
        <router-link id="link" to="/announcements">Announcements</router-link>
        <router-link id="link" to="/about">About</router-link>
        <router-link id="link" to="/contacts">Contact</router-link>
      </nav>
      <aside id="contact">
        <p>phone</p>
        <p>phone</p>
        <p>phone</p>
        <p>phone</p>
      </aside>
    </article>
    <p id="lower-details">&copy; 2026 Praise. All rights reserved.</p>
  </footer>
</template>

<style scoped>
.praiseFooter {
  display: flex;
  flex-direction: column;
  background: #040404;
  color: #fdfdfd;
  text-align: center;
  padding: 1rem;
  margin-top: auto;
  height: 20vh;
}

#components {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#footer-nav {
  display: flex;
  gap: 10px;
  font-size: medium;
}

#link {
  color: white;
  font-size: 1rem;
  text-decoration: none;
}

#contact {
  display: flex;
  flex-direction: column;
  font-size: 1rem;
  font-weight: 100;
}
</style>