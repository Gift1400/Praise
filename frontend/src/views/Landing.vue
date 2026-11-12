<template>
  <div class="landing-page">

    <!-- 1. Hero Section -->
    <header class="landing-hero" :style="{ backgroundImage: `url(${heroImg})` }">
      <div class="hero-overlay">
        <div class="hero-content">
          <div class="badge-tag">
            <span class="cross-symbol">✝</span>
            <span>WELCOME TO PRAISE CHURCH</span>
          </div>

          <h1 class="hero-title">
            Love God. Grow Together. <span class="highlight">Serve the City.</span>
          </h1>

          <p class="hero-description">
            A vibrant community rooted in authentic faith, heartfelt praise, and radical love.
            Whether you are exploring faith for the first time or searching for a home to grow in Christ,
            you have a family here.
          </p>

          <div class="hero-actions">
            <router-link to="/signup" class="btn-hero-primary">
              Join Our Church Family
            </router-link>
            <router-link to="/events" class="btn-hero-secondary">
              Upcoming Gatherings
            </router-link>
            <router-link to="/contacts" class="btn-hero-outline">
              Plan Your Visit →
            </router-link>
          </div>

          <!-- Quick Service Time Bar -->
          <div class="service-pill-bar">
            <div class="pill-item">
              <span class="pill-label">SUNDAYS</span>
              <span class="pill-val">09:00 AM &amp; 11:00 AM</span>
            </div>
            <div class="pill-divider"></div>
            <div class="pill-item">
              <span class="pill-label">WEDNESDAYS</span>
              <span class="pill-val">18:00 PM Prayer</span>
            </div>
            <div class="pill-divider"></div>
            <div class="pill-item">
              <span class="pill-label">LOCATION</span>
              <span class="pill-val">Cape Town, South Africa</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- 2. Core Pillars / Welcome Cards -->
    <section class="pillars-section">
      <div class="content-container">
        <div class="section-badge-header">
          <span class="sub-heading">WHO WE ARE</span>
          <h2>A Church Committed to Grace &amp; Truth</h2>
          <p class="section-intro">
            We are passionate about creating a space where anyone can encounter God, build lifelong friendships, and
            discover their calling.
          </p>
        </div>

        <div class="pillars-grid">
          <div class="pillar-card">
            <div class="pillar-icon-box">🎶</div>
            <h3>Christ-Centered Worship</h3>
            <p>
              Heartfelt, passionate worship gatherings that lift up Jesus and open doors for spiritual renewal and hope.
            </p>
          </div>

          <div class="pillar-card highlighted">
            <div class="pillar-icon-box">🤝</div>
            <h3>Authentic Fellowship</h3>
            <p>
              Life is better together. We connect through life groups, youth gatherings, and caring family circles.
            </p>
          </div>

          <div class="pillar-card">
            <div class="pillar-icon-box">🌍</div>
            <h3>Active City Outreach</h3>
            <p>
              Taking Christ’s love beyond church walls through food relief, student mentorship, and compassionate
              community care.
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- 3. What to Expect (With Gathering Image) -->
    <section class="expect-section">
      <div class="content-container expect-layout">
        <div class="expect-image-wrap">
          <img :src="gatheringImg" alt="Praise Church Gathering" class="expect-img" />
          <div class="expect-accent-card">
            <span class="accent-title">Everyone is Welcome</span>
            <p>Come as you are. Wear what is comfortable. There is a seat waiting for you.</p>
          </div>
        </div>

        <div class="expect-content">
          <span class="sub-heading">NEW TO PRAISE?</span>
          <h2>What to Expect on Your First Sunday</h2>
          <p class="expect-desc">
            Visiting a new church can feel intimidating, but we promise to make you feel right at home from the moment
            you pull into the parking lot.
          </p>

          <div class="expect-features">
            <div class="feature-item">
              <div class="feature-check">✓</div>
              <div>
                <strong>Warm Hospitality &amp; Coffee</strong>
                <p>Enjoy free coffee, tea, and warm smiles at our Welcome Lounge before and after each service.</p>
              </div>
            </div>

            <div class="feature-item">
              <div class="feature-check">✓</div>
              <div>
                <strong>Inspiring Biblical Messages</strong>
                <p>Engaging, practical sermons directly from God’s Word that empower your week and deepen your faith.
                </p>
              </div>
            </div>

            <div class="feature-item">
              <div class="feature-check">✓</div>
              <div>
                <strong>Safe &amp; Engaging Praise Kids</strong>
                <p>Dedicated teachers, safe classrooms, and age-tailored Bible adventures for toddlers through middle
                  school.</p>
              </div>
            </div>
          </div>

          <div class="expect-btn-row">
            <router-link to="/contacts" class="btn-primary">
              Plan Your Visit
            </router-link>
            <router-link to="/sermons" class="btn-text-link">
              Listen to Recent Sermons →
            </router-link>
          </div>
        </div>
      </div>
    </section>

    <!-- 4. Ministry Highlights Grid (Using Placeholder Images) -->
    <section class="ministries-section">
      <div class="content-container">
        <div class="section-badge-header">
          <span class="sub-heading">MINISTRIES &amp; LIFE</span>
          <h2>Get Connected in Every Season</h2>
          <p class="section-intro">
            From youth events to prayer gatherings and community outreach, there are countless ways to get involved.
          </p>
        </div>

        <div class="ministries-grid">
          <!-- Card 1: Sermons -->
          <article class="ministry-card">
            <div class="card-media">
              <img :src="campusImg" alt="Worship &amp; Teaching" />
              <span class="media-tag">Sermons</span>
            </div>
            <div class="card-body">
              <h3>Biblical Teaching</h3>
              <p>Explore inspiring messages preached by our pastors and leaders to feed your spiritual journey.</p>
              <router-link to="/sermons" class="card-link">Watch Sermons →</router-link>
            </div>
          </article>

          <!-- Card 2: Outreach -->
          <article class="ministry-card">
            <div class="card-media">
              <img :src="outreachImg" alt="Community Outreach" />
              <span class="media-tag">Outreach</span>
            </div>
            <div class="card-body">
              <h3>Community Outreach</h3>
              <p>Serve our neighbors through soup kitchens, care drives, and student discipleship teams.</p>
              <router-link to="/outreach" class="card-link">Volunteer Today →</router-link>
            </div>
          </article>

          <!-- Card 3: Events & Fellowship -->
          <article class="ministry-card">
            <div class="card-media">
              <img :src="hikeImg" alt="Fellowship Events" />
              <span class="media-tag">Events</span>
            </div>
            <div class="card-body">
              <h3>Events &amp; Fellowship</h3>
              <p>Check our calendar for retreats, worship nights, family picnics, and fellowship gatherings.</p>
              <router-link to="/events" class="card-link">See All Events →</router-link>
            </div>
          </article>

          <!-- Card 4: Prayer & Care -->
          <article class="ministry-card">
            <div class="card-media">
              <img :src="welcomeImg" alt="Prayer Wall" />
              <span class="media-tag">Care</span>
            </div>
            <div class="card-body">
              <h3>Prayer &amp; Pastoral Care</h3>
              <p>Our prayer team stands with you in faith. Share your requests or praise God through testimony.</p>
              <router-link to="/prayer" class="card-link">Share Prayer Request →</router-link>
            </div>
          </article>
        </div>
      </div>
    </section>

    <!-- 5. Pastoral Welcome Section -->
    <section class="pastoral-section">
      <div class="content-container pastoral-layout">
        <div class="pastor-image-wrap">
          <img :src="leaderImg" alt="Praise Church Pastoral Leadership" class="pastor-img" />
        </div>

        <div class="pastor-content">
          <span class="sub-heading">A MESSAGE FROM OUR LEADERSHIP</span>
          <h2>"You Belong Here"</h2>
          <blockquote class="pastor-quote">
            "At Praise Church, we believe no one was created to walk through life alone. Whatever chapter you find
            yourself in, God has a purpose for you, and we are excited to walk alongside you."
          </blockquote>
          <div class="pastor-signature">
            <strong>Pastor John &amp; The Leadership Team</strong>
            <span>Praise Church Ministries</span>
          </div>
          <div class="pastor-cta">
            <router-link to="/about" class="btn-outline">
              Learn More About Our Team
            </router-link>
          </div>
        </div>
      </div>
    </section>

    <!-- 6. Scripture & Life Seasons Banner -->
    <section class="scripture-callout">
      <div class="content-container callout-content">
        <span class="callout-eyebrow">FACING A DIFFICULT SEASON?</span>
        <h2>Find Scripture Promises for Your Life</h2>
        <p>Whether dealing with anxiety, grief, seeking guidance, or full of praise, explore God's Word curated for your
          moment.</p>
        <router-link to="/verses" class="btn-gold">
          Find a Verse for Today →
        </router-link>
      </div>
    </section>

    <!-- 7. Final Call to Action -->
    <section class="final-cta-section">
      <div class="content-container final-cta-wrap">
        <h2>Your Next Step Starts Today</h2>
        <p>Create an account to RSVP for upcoming events, join outreach teams, and connect with our church family.</p>
        <div class="cta-buttons">
          <router-link to="/signup" class="btn-primary-large">
            Create an Account
          </router-link>
          <router-link to="/contacts" class="btn-secondary-large">
            Contact Pastoral Team
          </router-link>
        </div>
      </div>
    </section>

  </div>
</template>

<script setup>
// Load reference images from the local images directory
const heroImg = new URL("../assets/images/welcome.jpeg", import.meta.url).href
const gatheringImg = new URL("../assets/images/Gathering.jpeg", import.meta.url).href
const campusImg = new URL("../assets/images/Campus-meeting.jpeg", import.meta.url).href
const outreachImg = new URL("../assets/images/commity-image.jpeg", import.meta.url).href
const hikeImg = new URL("../assets/images/Hike.jpeg", import.meta.url).href
const leaderImg = new URL("../assets/images/Leader1.jpeg", import.meta.url).href
const welcomeImg = new URL("../assets/images/welcome.jpeg", import.meta.url).href
</script>

<style scoped>
.landing-page {
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
  background-color: #fdfcf9;
  overflow-x: hidden;
}

.content-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

.sub-heading {
  font-size: 13px;
  letter-spacing: 3px;
  text-transform: uppercase;
  color: #9b6d00;
  font-weight: 700;
  display: block;
  margin-bottom: 10px;
}

/* 1. Hero Section */
.landing-hero {
  min-height: 94vh;
  background-size: cover;
  background-position: center;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

.hero-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(rgba(18, 14, 9, 0.75),
      rgba(18, 14, 9, 0.88));
  display: flex;
  align-items: center;
  justify-content: center;
  padding: calc(12vh + 40px) 24px 60px;
}

.hero-content {
  max-width: 860px;
  text-align: center;
  color: #ffffff;
}

.badge-tag {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  background: rgba(255, 255, 255, 0.12);
  border: 1px solid rgba(255, 255, 255, 0.25);
  padding: 6px 16px;
  border-radius: 20px;
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 1.5px;
  margin-bottom: 24px;
}

.cross-symbol {
  color: #dfba73;
}

.hero-title {
  font-family: Georgia, serif;
  font-size: 58px;
  line-height: 1.15;
  font-weight: 500;
  margin: 0 0 22px;
}

.hero-title .highlight {
  color: #dfba73;
  font-style: italic;
}

.hero-description {
  font-size: 17.5px;
  line-height: 1.8;
  color: #e5ded2;
  margin: 0 auto 36px;
  max-width: 720px;
}

.hero-actions {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 16px;
  flex-wrap: wrap;
  margin-bottom: 48px;
}

.btn-hero-primary {
  background: #9b6d00;
  color: #ffffff;
  padding: 14px 30px;
  border-radius: 30px;
  font-weight: 600;
  font-size: 15px;
  text-decoration: none;
  transition: all 0.2s ease;
  box-shadow: 0 4px 16px rgba(155, 109, 0, 0.35);
}

.btn-hero-primary:hover {
  background: #b08d57;
  transform: translateY(-2px);
}

.btn-hero-secondary {
  background: rgba(255, 255, 255, 0.15);
  color: #ffffff;
  border: 1px solid rgba(255, 255, 255, 0.3);
  padding: 14px 28px;
  border-radius: 30px;
  font-weight: 600;
  font-size: 15px;
  text-decoration: none;
  backdrop-filter: blur(4px);
  transition: all 0.2s ease;
}

.btn-hero-secondary:hover {
  background: rgba(255, 255, 255, 0.25);
  transform: translateY(-2px);
}

.btn-hero-outline {
  color: #dfba73;
  font-size: 15px;
  font-weight: 600;
  text-decoration: none;
  padding: 14px 18px;
  transition: opacity 0.2s;
}

.btn-hero-outline:hover {
  opacity: 0.8;
}

.service-pill-bar {
  display: inline-flex;
  align-items: center;
  gap: 24px;
  background: rgba(0, 0, 0, 0.45);
  border: 1px solid rgba(255, 255, 255, 0.16);
  backdrop-filter: blur(8px);
  padding: 14px 30px;
  border-radius: 40px;
  flex-wrap: wrap;
  justify-content: center;
}

.pill-item {
  display: flex;
  flex-direction: column;
  text-align: left;
}

.pill-label {
  font-size: 10px;
  font-weight: bold;
  letter-spacing: 1.5px;
  color: #dfba73;
}

.pill-val {
  font-size: 13.5px;
  color: #ffffff;
  font-weight: 600;
}

.pill-divider {
  width: 1px;
  height: 28px;
  background: rgba(255, 255, 255, 0.18);
}

/* 2. Pillars Section */
.pillars-section {
  padding: 90px 0;
  background: #ffffff;
}

.section-badge-header {
  text-align: center;
  max-width: 680px;
  margin: 0 auto 50px;
}

.section-badge-header h2 {
  font-family: Georgia, serif;
  font-size: 38px;
  margin: 0 0 14px;
  color: #2f281f;
}

.section-intro {
  font-size: 15px;
  line-height: 1.7;
  color: #6d6455;
  margin: 0;
}

.pillars-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 28px;
}

.pillar-card {
  background: #faf8f3;
  border: 1px solid #ede8de;
  border-radius: 18px;
  padding: 38px 30px;
  transition: transform 0.2s, box-shadow 0.2s;
}

.pillar-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.06);
}

.pillar-card.highlighted {
  background: #ffffff;
  border-color: #9b6d00;
  box-shadow: 0 8px 25px rgba(155, 109, 0, 0.1);
}

.pillar-icon-box {
  font-size: 38px;
  margin-bottom: 20px;
}

.pillar-card h3 {
  font-family: Georgia, serif;
  font-size: 22px;
  margin: 0 0 12px;
  color: #2f281f;
}

.pillar-card p {
  font-size: 14px;
  line-height: 1.7;
  color: #665d4f;
  margin: 0;
}

/* 3. Expect Section */
.expect-section {
  padding: 90px 0;
  background: #fdfcf9;
}

.expect-layout {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 56px;
  align-items: center;
}

.expect-image-wrap {
  position: relative;
}

.expect-img {
  width: 100%;
  border-radius: 20px;
  display: block;
  box-shadow: 0 16px 40px rgba(0, 0, 0, 0.1);
  object-fit: cover;
  max-height: 520px;
}

.expect-accent-card {
  position: absolute;
  bottom: -24px;
  right: -16px;
  background: #9b6d00;
  color: #ffffff;
  padding: 22px 24px;
  border-radius: 14px;
  max-width: 280px;
  box-shadow: 0 8px 24px rgba(155, 109, 0, 0.35);
}

.accent-title {
  display: block;
  font-family: Georgia, serif;
  font-size: 17px;
  font-weight: 600;
  margin-bottom: 6px;
}

.expect-accent-card p {
  font-size: 12.5px;
  line-height: 1.5;
  margin: 0;
  opacity: 0.95;
}

.expect-content h2 {
  font-family: Georgia, serif;
  font-size: 38px;
  margin: 0 0 16px;
  color: #2f281f;
}

.expect-desc {
  font-size: 15px;
  line-height: 1.75;
  color: #685e50;
  margin: 0 0 30px;
}

.expect-features {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-bottom: 36px;
}

.feature-item {
  display: flex;
  gap: 16px;
}

.feature-check {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: #fbf5ea;
  color: #9b6d00;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 14px;
  flex-shrink: 0;
}

.feature-item strong {
  display: block;
  font-size: 15px;
  color: #2f281f;
  margin-bottom: 4px;
}

.feature-item p {
  font-size: 13.5px;
  color: #6e6454;
  margin: 0;
  line-height: 1.6;
}

.expect-btn-row {
  display: flex;
  align-items: center;
  gap: 20px;
  flex-wrap: wrap;
}

.btn-primary {
  background: #9b6d00;
  color: #ffffff;
  padding: 12px 26px;
  border-radius: 24px;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-primary:hover {
  background: #805a00;
}

.btn-text-link {
  color: #9b6d00;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
}

.btn-text-link:hover {
  text-decoration: underline;
}

/* 4. Ministries Grid */
.ministries-section {
  padding: 90px 0;
  background: #ffffff;
}

.ministries-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
}

.ministry-card {
  background: #faf8f4;
  border: 1px solid #ece6db;
  border-radius: 16px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  transition: transform 0.2s, box-shadow 0.2s;
}

.ministry-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
}

.card-media {
  position: relative;
  height: 180px;
}

.card-media img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.media-tag {
  position: absolute;
  top: 12px;
  left: 12px;
  background: rgba(0, 0, 0, 0.7);
  color: #dfba73;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 1px;
  text-transform: uppercase;
  padding: 4px 10px;
  border-radius: 12px;
  backdrop-filter: blur(4px);
}

.card-body {
  padding: 22px;
  display: flex;
  flex-direction: column;
  flex: 1;
}

.card-body h3 {
  font-family: Georgia, serif;
  font-size: 19px;
  margin: 0 0 10px;
  color: #2f281f;
}

.card-body p {
  font-size: 13px;
  color: #685f52;
  line-height: 1.6;
  margin: 0 0 16px;
  flex: 1;
}

.card-link {
  color: #9b6d00;
  font-size: 13.5px;
  font-weight: 600;
  text-decoration: none;
}

.card-link:hover {
  text-decoration: underline;
}

/* 5. Pastoral Section */
.pastoral-section {
  padding: 90px 0;
  background: #faf7f0;
}

.pastoral-layout {
  display: grid;
  grid-template-columns: 420px 1fr;
  gap: 56px;
  align-items: center;
}

.pastor-image-wrap img {
  width: 100%;
  border-radius: 20px;
  object-fit: cover;
  box-shadow: 0 16px 35px rgba(0, 0, 0, 0.1);
}

.pastor-content h2 {
  font-family: Georgia, serif;
  font-size: 38px;
  margin: 0 0 20px;
  color: #2f281f;
}

.pastor-quote {
  font-family: Georgia, serif;
  font-size: 20px;
  line-height: 1.7;
  color: #4f4536;
  margin: 0 0 24px;
  font-style: italic;
  border-left: 3px solid #9b6d00;
  padding-left: 20px;
}

.pastor-signature strong {
  display: block;
  font-size: 16px;
  color: #2f281f;
}

.pastor-signature span {
  font-size: 13px;
  color: #807360;
}

.pastor-cta {
  margin-top: 28px;
}

.btn-outline {
  display: inline-block;
  border: 1.5px solid #9b6d00;
  color: #9b6d00;
  padding: 10px 22px;
  border-radius: 22px;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-outline:hover {
  background: #9b6d00;
  color: #ffffff;
}

/* 6. Scripture Callout */
.scripture-callout {
  padding: 80px 0;
  background:
    linear-gradient(rgba(24, 18, 12, 0.8), rgba(24, 18, 12, 0.9)),
    linear-gradient(135deg, #7c5f3b 0%, #2e2316 100%);
  color: #ffffff;
  text-align: center;
}

.callout-content {
  max-width: 680px;
}

.callout-eyebrow {
  font-size: 12px;
  letter-spacing: 3px;
  color: #dfba73;
  font-weight: 700;
  display: block;
  margin-bottom: 12px;
}

.scripture-callout h2 {
  font-family: Georgia, serif;
  font-size: 36px;
  margin: 0 0 16px;
}

.scripture-callout p {
  font-size: 15px;
  color: #dfd7cc;
  line-height: 1.7;
  margin: 0 auto 28px;
}

.btn-gold {
  display: inline-block;
  background: #dfba73;
  color: #2a2012;
  padding: 13px 28px;
  border-radius: 25px;
  font-size: 14px;
  font-weight: 700;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-gold:hover {
  background: #ecd49e;
  transform: translateY(-2px);
}

/* 7. Final CTA */
.final-cta-section {
  padding: 90px 0;
  background: #ffffff;
  text-align: center;
}

.final-cta-wrap {
  max-width: 650px;
}

.final-cta-wrap h2 {
  font-family: Georgia, serif;
  font-size: 38px;
  margin: 0 0 14px;
  color: #2f281f;
}

.final-cta-wrap p {
  font-size: 15px;
  line-height: 1.7;
  color: #6a6051;
  margin: 0 0 32px;
}

.cta-buttons {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 16px;
  flex-wrap: wrap;
}

.btn-primary-large {
  background: #9b6d00;
  color: #ffffff;
  padding: 14px 34px;
  border-radius: 28px;
  font-size: 15px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
  box-shadow: 0 4px 15px rgba(155, 109, 0, 0.3);
}

.btn-primary-large:hover {
  background: #805a00;
  transform: translateY(-2px);
}

.btn-secondary-large {
  background: #f3eee4;
  color: #4a3e2e;
  padding: 14px 30px;
  border-radius: 28px;
  font-size: 15px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-secondary-large:hover {
  background: #e8e0d2;
}

/* Responsive */
@media (max-width: 1024px) {
  .pillars-grid {
    grid-template-columns: 1fr;
  }

  .expect-layout {
    grid-template-columns: 1fr;
  }

  .expect-accent-card {
    right: 20px;
    bottom: -16px;
  }

  .ministries-grid {
    grid-template-columns: 1fr 1fr;
  }

  .pastoral-layout {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 640px) {
  .hero-title {
    font-size: 38px;
  }

  .ministries-grid {
    grid-template-columns: 1fr;
  }

  .service-pill-bar {
    border-radius: 18px;
    gap: 14px;
  }

  .pill-divider {
    display: none;
  }
}
</style>
