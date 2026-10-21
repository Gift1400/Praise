<template>
  <div class="contact-page">

    <!-- Hero Section -->
    <section class="contact-hero">
      <div class="hero-content">
        <p class="welcome-text">WE WOULD LOVE TO HEAR FROM YOU</p>
        <h1>Contact Praise Church</h1>
        <p>
          Whether you have a question, need prayer, would like to visit us,
          or simply want to connect with our church family, we are here for you.
        </p>
      </div>
    </section>

    <!-- Contact Information -->
    <section class="contact-section">
      <div class="section-heading">
        <p>GET IN TOUCH</p>
        <h2>We’re Here For You</h2>
        <span></span>
      </div>

      <div class="contact-container">

        <!-- Phone -->
        <div class="contact-card">
          <div class="icon">☎</div>
          <h3>Call Us</h3>
          <p>Speak to us directly for any enquiries.</p>
          <a href="tel:+27693721131">+27 69 372 1131</a>
        </div>

        <!-- Email -->
        <div class="contact-card">
          <div class="icon">✉</div>
          <h3>Email Us</h3>
          <p>Send us a message and we will get back to you.</p>
          <a href="mailto:info@praisechurch.org">
            info@praisechurch.org
          </a>
        </div>

        <!-- Location -->
        <div class="contact-card">
          <div class="icon">📍</div>
          <h3>Visit Us</h3>
          <p>Come worship with us at our church branch.</p>
          <p class="contact-detail">
            Church Address<br />
            Cape Town, South Africa
          </p>
        </div>

        <!-- Service Times -->
        <div class="contact-card">
          <div class="icon">⏰</div>
          <h3>Service Times</h3>
          <p>Join us for worship and fellowship.</p>
          <p class="contact-detail">
            Sunday Service: 09:00<br />
            Bible Study: Wednesday 18:00
          </p>
        </div>

      </div>
    </section>

    <!-- Contact Form -->
    <section class="message-section">
      <div class="message-container">

        <div class="message-info">
          <p class="small-heading">SEND US A MESSAGE</p>
          <h2>We'd Love To Hear From You</h2>

          <p>
            Have a question, prayer request, testimony or need support from
            the church? Complete the form and our team will get back to you.
          </p>

          <div class="message-note">
            <strong>Need prayer?</strong>
            <p>
              You can also submit a prayer request through our Prayer Request
              section.
            </p>
          </div>
        </div>

        <form class="contact-form" @submit.prevent>
          <div class="form-row">
            <div class="form-group">
              <label for="name">Full Name</label>
              <input
                id="name"
                type="text"
                placeholder="Enter your name"
              />
            </div>

            <div class="form-group">
              <label for="email">Email Address</label>
              <input
                id="email"
                type="email"
                placeholder="Enter your email"
              />
            </div>
          </div>

          <div class="form-group">
            <label for="subject">Subject</label>
            <input
              id="subject"
              type="text"
              placeholder="What would you like to talk about?"
            />
          </div>

          <div class="form-group">
            <label for="message">Message</label>
            <textarea
              id="message"
              rows="6"
              placeholder="Write your message here..."
            ></textarea>
          </div>

          <button type="submit">SEND MESSAGE</button>
        </form>

      </div>
    </section>

    <!-- Social Media -->
    <section class="social-section">
      <p class="small-heading">STAY CONNECTED</p>
      <h2>Follow Praise Church</h2>
      <p>
        Connect with us online and stay up to date with sermons,
        announcements, events and church activities.
      </p>

      <div class="social-links">
        <a href="#" target="_blank">Facebook</a>
        <a href="#" target="_blank">Instagram</a>
        <a href="#" target="_blank">YouTube</a>
        <a href="#" target="_blank">TikTok</a>
      </div>
    </section>

  </div>
</template>

<script>
import "./Style1.css";
</script>
