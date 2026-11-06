<template>
  <footer class="praise-footer">
    <div class="footer-container">
      <div class="footer-grid">
        <div class="footer-col brand-col">
          <h2 class="footer-logo">Pra✝se</h2>
          <p class="tagline">A community rooted in faith, love and praise.</p>
        </div>

        <div class="footer-col links-col">
          <h3 class="col-title">Quick Links</h3>
          <ul class="footer-links">
            <li><router-link to="/">Home</router-link></li>
            <li><router-link to="/sermons">Sermons</router-link></li>
            <li><router-link to="/events">Events</router-link></li>
            <li><router-link to="/announcements">Announcements</router-link></li>
            <li><router-link to="/about">About</router-link></li>
            <li><router-link to="/contacts">Contact</router-link></li>
          </ul>
        </div>

        <div class="footer-col services-col">
          <h3 class="col-title">Service Times</h3>
          <ul class="service-times">
            <li>
              <span class="service-name">Sunday Service</span>
              <span class="service-time">09:00</span>
            </li>
            <li>
              <span class="service-name">Bible Study</span>
              <span class="service-time">Wednesday 18:00</span>
            </li>
            <li>
              <span class="service-name">Youth Friday</span>
              <span class="service-time">Friday 17:00</span>
            </li>
          </ul>
        </div>

         <div class="footer-col contact-col">
          <h3 class="col-title">Contact Info</h3>
          <div class="contact-items">
            <div class="contact-item">
              <img
                src="https://cdn-icons-png.flaticon.com/512/455/455705.png"
                alt="Phone icon"
                class="footer-icon"
              />
              <a href="tel:+27693721131">+27 69 372 1131</a>
            </div>
            <div class="contact-item">
              <img
                src="https://cdn-icons-png.flaticon.com/512/542/542689.png"
                alt="Email icon"
                class="footer-icon"
              />
              <a href="mailto:info@praisechurch.org">info@praisechurch.org</a>
            </div>
            <div class="contact-item">
              <img
                src="https://cdn-icons-png.flaticon.com/512/684/684809.png"
                alt="Location icon"
                class="footer-icon"
              />
              <span>Cape Town, South Africa</span>
            </div>
          </div>
        </div>
      </div>

      <div class="footer-bottom">
        <p class="copyright">&copy; 2026 Praise Church. All rights reserved.</p>
        <div class="social-links">
          <a
            href="https://facebook.com"
            target="_blank"
            rel="noopener noreferrer"
            class="social-link"
            aria-label="Facebook"
          >
            <img
              src="https://cdn-icons-png.flaticon.com/512/733/733547.png"
              alt="Facebook"
              class="social-icon"
            />
          </a>
          <a
            href="https://instagram.com"
            target="_blank"
            rel="noopener noreferrer"
            class="social-link"
            aria-label="Instagram"
          >
            <img
              src="https://cdn-icons-png.flaticon.com/512/2111/2111463.png"
              alt="Instagram"
              class="social-icon"
            />
          </a>
          <a
            href="https://youtube.com"
            target="_blank"
            rel="noopener noreferrer"
            class="social-link"
            aria-label="YouTube"
          >
            <img
              src="https://cdn-icons-png.flaticon.com/512/1384/1384060.png"
              alt="YouTube"
              class="social-icon"
            />
          </a>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
</script>

<style scoped>
.praise-footer {
  background-color: #111111;
  color: #ffffff;
  padding: 4rem 1.5rem 2rem 1.5rem;
  margin-top: auto;
  font-family: Arial, Helvetica, sans-serif;
  border-top: 1px solid rgba(176, 141, 87, 0.2);
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 3rem;
}

.footer-grid {
  display: grid;
  grid-template-columns: 1.5fr 1fr 1.2fr 1.3fr;
  gap: 2.5rem;
}

.footer-col {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.footer-logo {
  font-size: 2.5rem;
  font-weight: 700;
  color: #ffffff;
  letter-spacing: 1px;
  line-height: 1.1;
  margin: 0;
  user-select: none;
}

.tagline {
  color: #c4c4c4;
  font-size: 0.95rem;
  line-height: 1.6;
  max-width: 280px;
  margin: 0;
}

.col-title {
  color: #b08d57;
  font-size: 1.15rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 0;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid rgba(176, 141, 87, 0.3);
  display: inline-block;
  align-self: flex-start;
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 0.65rem;
}

.footer-links a {
  color: #e0e0e0;
  text-decoration: none;
  font-size: 0.95rem;
  transition: color 0.2s ease, transform 0.2s ease;
  display: inline-block;
}

.footer-links a:hover {
  color: #b08d57;
  transform: translateX(4px);
}

.service-times {
  list-style: none;
  padding: 0;
  margin: 0;
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.service-times li {
  display: flex;
  flex-direction: column;
  gap: 0.2rem;
}

.service-name {
  color: #ffffff;
  font-weight: 600;
  font-size: 0.95rem;
}

.service-time {
  color: #b08d57;
  font-size: 0.88rem;
}

.contact-items {
  display: flex;
  flex-direction: column;
  gap: 0.85rem;
}

.contact-item {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  color: #e0e0e0;
  font-size: 0.95rem;
  line-height: 1.4;
}

.footer-icon {
  width: 18px;
  height: 18px;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
  flex-shrink: 0;
}

.contact-item a {
  color: #e0e0e0;
  text-decoration: none;
  transition: color 0.2s ease;
}

.contact-item a:hover {
  color: #b08d57;
}

.footer-bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 2rem;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  flex-wrap: wrap;
  gap: 1.5rem;
}

.copyright {
  color: #999999;
  font-size: 0.88rem;
  margin: 0;
}

.social-links {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.social-link {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.08);
  transition: background-color 0.2s ease, transform 0.2s ease;
}

.social-link:hover {
  background-color: #b08d57;
  transform: translateY(-2px);
}

.social-icon {
  width: 18px;
  height: 18px;
  filter: brightness(0) invert(1);
}

/* Responsive */
@media (max-width: 850px) {
  .footer-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 2.5rem 2rem;
  }
}

@media (max-width: 600px) {
  .praise-footer {
    padding: 3rem 1rem 1.5rem 1rem;
  }

  .footer-grid {
    grid-template-columns: 1fr;
    gap: 2rem;
  }

  .footer-logo {
    font-size: 2.2rem;
  }

  .footer-bottom {
    flex-direction: column;
    text-align: center;
    gap: 1rem;
  }
}
</style>