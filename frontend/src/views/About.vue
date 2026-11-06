<template>
  <div class="about-page">
   
    <section class="about-hero">
      <div class="hero-content">
        <span class="hero-subtitle">WHO WE ARE</span>
        <h1 class="hero-title">About Praise Church</h1>
        <div class="gold-divider center"></div>
        <p class="hero-desc">
          Dedicated to proclaiming God's Word, transforming lives through authentic worship, and building a united family anchored in Christ.
        </p>
      </div>
    </section>

   
    <section class="story-section">
      <div class="section-container story-container">
        <div class="story-text-col">
          <span class="sub-heading">OUR JOURNEY</span>
          <h2 class="section-title">Our Story</h2>
          <div class="gold-divider"></div>
          <p class="story-p">
            Founded with a passion to share the Gospel and foster genuine Christian fellowship, Praise Church began as a gathering of believers united in prayer and worship. Over the years, God has graciously grown our ministry into a vibrant and welcoming church family.
          </p>
          <p class="story-p">
            Our mission is to know Jesus Christ and make Him known. We are committed to discipleship, spiritual growth, and being a beacon of hope, restoration, and compassion to our city and beyond.
          </p>
          <p class="story-p secondary">
            Every person is created in God's image with purpose. Through worship, practical teachings, and compassionate outreach, we invite you to find your place in God's kingdom and walk together with us in faith.
          </p>
        </div>
        <div class="story-image-col">
          <div class="story-image-wrapper">
            <img :src="welcomeImg" alt="Praise Church Family" class="story-img" />
          </div>
        </div>
      </div>
    </section>

   
    <section class="values-section">
      <div class="section-container">
        <div class="section-header">
          <span class="sub-heading">WHAT WE STAND FOR</span>
          <h2 class="section-title">Our Core Values</h2>
          <div class="gold-divider center"></div>
          <p class="section-subtitle">The foundational principles that guide everything we do as a church body.</p>
        </div>

        <div class="values-grid">
         
          <div class="value-card">
            <div class="value-icon-box">
              <img
                src="https://cdn-icons-png.flaticon.com/512/1076/1076735.png"
                alt="Faith icon"
                class="value-icon"
              />
            </div>
            <h3 class="value-title">Faith</h3>
            <p class="value-desc">
              We stand firmly on the unshakeable truth of God's Word, trusting in Jesus Christ as our Lord and Savior in every season of life.
            </p>
          </div>

          <!-- Community -->
          <div class="value-card">
            <div class="value-icon-box">
              <img
                src="https://cdn-icons-png.flaticon.com/512/476/476863.png"
                alt="Community icon"
                class="value-icon"
              />
            </div>
            <h3 class="value-title">Community</h3>
            <p class="value-desc">
              We cultivate a warm, authentic spiritual family where every individual is welcomed, valued, and encouraged to grow together.
            </p>
          </div>

         
          <div class="value-card">
            <div class="value-icon-box">
              <img
                src="https://cdn-icons-png.flaticon.com/512/2910/2910795.png"
                alt="Service icon"
                class="value-icon"
              />
            </div>
            <h3 class="value-title">Service</h3>
            <p class="value-desc">
              Following the example of Jesus, we lead with humility and joyful service, bringing practical support and hope to our local communities.
            </p>
          </div>

         
          <div class="value-card">
            <div class="value-icon-box">
              <img
                src="https://cdn-icons-png.flaticon.com/512/833/833472.png"
                alt="Love icon"
                class="value-icon"
              />
            </div>
            <h3 class="value-title">Love</h3>
            <p class="value-desc">
              Unconditional love is the hallmark of our faith. We seek to love God wholeheartedly and extend that grace and compassion to all people.
            </p>
          </div>
        </div>
      </div>
    </section>

   
    <section class="leadership-section">
      <div class="section-container">
        <div class="section-header">
          <span class="sub-heading">SHEPHERDING OUR FLOCK</span>
          <h2 class="section-title">Our Leaders</h2>
          <div class="gold-divider center"></div>
          <p class="section-subtitle">Dedicated servants guiding our community with prayer, biblical truth, and pastoral care.</p>
        </div>

        <div v-if="leadersLoading" class="status-msg">
          <p>Loading leaders...</p>
        </div>

        <div v-else-if="leaders.length === 0" class="placeholder-box">
          <p class="placeholder-text">
            Our pastoral and leadership team is committed to serving the church family. Leadership profiles will be updated shortly.
          </p>
        </div>

        <div v-else class="leaders-grid">
          <article v-for="leader in leaders" :key="leader.id || leader.leaderId" class="leader-card">
            <div class="leader-avatar-wrapper">
              <img
                :src="leader.photoUrl || leader.imageUrl || 'https://cdn-icons-png.flaticon.com/512/3135/3135715.png'"
                :alt="leaderName(leader)"
                class="leader-avatar"
              />
            </div>
            <div class="leader-info">
              <h3 class="leader-name">{{ leaderName(leader) }}</h3>
              <p class="leader-role">{{ leaderRole(leader) }}</p>
              <p class="leader-bio" v-if="leaderBio(leader)">{{ leaderBio(leader) }}</p>
            </div>
          </article>
        </div>
      </div>
    </section>

    
    <section class="sites-section">
      <div class="section-container">
        <div class="section-header dark">
          <span class="sub-heading gold">FIND US</span>
          <h2 class="section-title white">Our Locations</h2>
          <div class="gold-divider center"></div>
          <p class="section-subtitle muted">Join us at one of our welcoming worship locations across the region.</p>
        </div>

        <div v-if="sitesLoading" class="status-msg dark">
          <p>Loading locations...</p>
        </div>

        <div v-else-if="sites.length === 0" class="placeholder-box dark">
          <p class="placeholder-text">
            Our main sanctuary is located in Cape Town, South Africa. Contact our church office for directions and service details.
          </p>
        </div>

        <div v-else class="sites-grid">
          <article v-for="site in sites" :key="site.id || site.siteId" class="site-card">
            <div class="site-icon-box">
              <img
                src="https://cdn-icons-png.flaticon.com/512/2972/2972535.png"
                alt="Church building icon"
                class="site-icon"
              />
            </div>
            <div class="site-details">
              <h3 class="site-name">{{ site.churchName || site.name || 'Praise Church Campus' }}</h3>
              <div class="site-meta" v-if="site.location || site.address">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/684/684809.png"
                  alt="Location icon"
                  class="meta-icon"
                />
                <span>{{ site.location || site.address }}</span>
              </div>
              <div class="site-meta" v-if="site.contactNumber || site.phone">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/455/455705.png"
                  alt="Phone icon"
                  class="meta-icon"
                />
                <span>{{ site.contactNumber || site.phone }}</span>
              </div>
            </div>
          </article>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const welcomeImg = new URL("../assets/images/welcome.jpeg", import.meta.url).href

const leaders = ref([])
const leadersLoading = ref(true)

const sites = ref([])
const sitesLoading = ref(true)

function leaderName(leader) {
  if (leader.name) return leader.name
  if (leader.firstName || leader.lastName) {
    return `${leader.firstName || ''} ${leader.lastName || ''}`.trim()
  }
  return 'Church Leader'
}

function leaderRole(leader) {
  return leader.role || leader.position || leader.title || 'Pastoral Team'
}

function leaderBio(leader) {
  return leader.bio || leader.biography || leader.description || ''
}

async function fetchLeaders() {
  try {
    const res = await fetch('/api/leader/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data)) {
        leaders.value = data
      }
    }
  } catch (err) {
    console.error('Error fetching leaders:', err)
  } finally {
    leadersLoading.value = false
  }
}

async function fetchSites() {
  try {
    const res = await fetch('/api/churchSite/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data)) {
        sites.value = data
      }
    }
  } catch (err) {
    console.error('Error fetching church sites:', err)
  } finally {
    sitesLoading.value = false
  }
}

onMounted(() => {
  fetchLeaders()
  fetchSites()
})
</script>

<style scoped>
.about-page {
  font-family: Arial, Helvetica, sans-serif;
  color: #222222;
  background-color: #ffffff;
  overflow-x: hidden;
}

.about-hero {
  min-height: 520px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 50px) 2rem 5rem 2rem;
  background:
    linear-gradient(rgba(10, 10, 15, 0.65), rgba(10, 10, 15, 0.65)),
    url("https://images.unsplash.com/photo-1510936111840-65e151ad71bb?auto=format&fit=crop&w=1600&q=80");
  background-size: cover;
  background-position: center;
  color: #ffffff;
}

.hero-content {
  max-width: 800px;
  margin: 0 auto;
}

.hero-subtitle {
  display: block;
  font-size: 0.9rem;
  font-weight: 700;
  letter-spacing: 3px;
  color: #b08d57;
  text-transform: uppercase;
  margin-bottom: 0.8rem;
}

.hero-title {
  font-size: clamp(2.5rem, 5vw, 4rem);
  font-weight: 700;
  margin: 0 0 1rem 0;
  line-height: 1.15;
  color: #ffffff;
}

.hero-desc {
  font-size: clamp(1.05rem, 2vw, 1.25rem);
  line-height: 1.7;
  color: #e0e0e0;
  margin: 1.5rem auto 0 auto;
  max-width: 680px;
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
}

.section-header {
  text-align: center;
  margin-bottom: 3.5rem;
}

.sub-heading {
  display: block;
  font-size: 0.85rem;
  letter-spacing: 2px;
  font-weight: 700;
  color: #9b6d00;
  text-transform: uppercase;
  margin-bottom: 0.5rem;
}

.sub-heading.gold {
  color: #e3901c;
}

.section-title {
  font-size: clamp(2rem, 3.8vw, 2.7rem);
  font-weight: 700;
  color: #1a1a2e;
  margin: 0;
  line-height: 1.25;
}

.section-title.white {
  color: #ffffff;
}

.gold-divider {
  width: 60px;
  height: 3px;
  background-color: #b08d57;
  margin: 0.6rem 0 1.5rem 0;
  border-radius: 2px;
}

.gold-divider.center {
  margin: 0.6rem auto 1.5rem auto;
}

.section-subtitle {
  font-size: 1.05rem;
  color: #666666;
  max-width: 620px;
  margin: 0 auto;
  line-height: 1.6;
}

.section-subtitle.muted {
  color: #b3b3b3;
}

.story-section {
  background-color: #ffffff;
  padding: 6rem 2rem;
}

.story-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 4rem;
  align-items: center;
}

.story-text-col {
  display: flex;
  flex-direction: column;
}

.story-p {
  font-size: 1.05rem;
  line-height: 1.8;
  color: #444444;
  margin: 0 0 1.25rem 0;
}

.story-p.secondary {
  color: #666666;
}

.story-image-col {
  display: flex;
  justify-content: center;
}

.story-image-wrapper {
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.12);
  width: 100%;
  max-height: 480px;
}

.story-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.5s ease;
}

.story-image-wrapper:hover .story-img {
  transform: scale(1.03);
}

.values-section {
  background-color: #dbdbda;
  padding: 6rem 2rem;
}

.values-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1.8rem;
}

.value-card {
  background-color: #ffffff;
  border-radius: 16px;
  padding: 2.2rem 1.8rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  border: 1px solid rgba(176, 141, 87, 0.15);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
  transition: transform 0.3s ease, box-shadow 0.3s ease, border-color 0.3s ease;
}

.value-card:hover {
  transform: translateY(-6px);
  box-shadow: 0 14px 30px rgba(0, 0, 0, 0.1);
  border-color: #b08d57;
}

.value-icon-box {
  width: 68px;
  height: 68px;
  border-radius: 50%;
  background-color: rgba(176, 141, 87, 0.12);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1.4rem;
}

.value-icon {
  width: 32px;
  height: 32px;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
}

.value-title {
  font-size: 1.3rem;
  font-weight: 700;
  color: #1a1a2e;
  margin: 0 0 0.8rem 0;
}

.value-desc {
  font-size: 0.95rem;
  line-height: 1.6;
  color: #666666;
  margin: 0;
}

.leadership-section {
  background-color: #ffffff;
  padding: 6rem 2rem;
}

.leaders-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 2.2rem;
}

.leader-card {
  background-color: #fafafa;
  border-radius: 16px;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  border: 1px solid rgba(0, 0, 0, 0.06);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.04);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.leader-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
}

.leader-avatar-wrapper {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
  margin-bottom: 1.25rem;
  border: 3px solid #b08d57;
  background-color: #f0f0f0;
}

.leader-avatar {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.leader-name {
  font-size: 1.25rem;
  font-weight: 700;
  color: #1a1a2e;
  margin: 0 0 0.35rem 0;
}

.leader-role {
  font-size: 0.92rem;
  font-weight: 600;
  color: #b08d57;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  margin: 0 0 0.85rem 0;
}

.leader-bio {
  font-size: 0.92rem;
  line-height: 1.6;
  color: #666666;
  margin: 0;
}

.sites-section {
  background-color: #16213e;
  padding: 6rem 2rem;
}

.sites-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
}

.site-card {
  background-color: rgba(255, 255, 255, 0.04);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 16px;
  padding: 2.2rem;
  display: flex;
  gap: 1.5rem;
  align-items: flex-start;
  transition: all 0.3s ease;
}

.site-card:hover {
  background-color: rgba(255, 255, 255, 0.08);
  border-color: #e3901c;
  transform: translateY(-4px);
}

.site-icon-box {
  width: 54px;
  height: 54px;
  border-radius: 12px;
  background-color: rgba(227, 144, 28, 0.15);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.site-icon {
  width: 28px;
  height: 28px;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
}

.site-details {
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
}

.site-name {
  color: #ffffff;
  font-size: 1.3rem;
  font-weight: 600;
  margin: 0;
}

.site-meta {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.95rem;
  color: #cccccc;
}

.meta-icon {
  width: 15px;
  height: 15px;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
  flex-shrink: 0;
}

.status-msg {
  text-align: center;
  padding: 3rem 1rem;
  color: #666666;
  font-size: 1.05rem;
}

.status-msg.dark {
  color: #aaaaaa;
}

.placeholder-box {
  max-width: 650px;
  margin: 0 auto;
  text-align: center;
  padding: 3rem 2rem;
  border: 1px dashed rgba(176, 141, 87, 0.4);
  border-radius: 16px;
  background-color: #faf8f5;
}

.placeholder-box.dark {
  background-color: rgba(255, 255, 255, 0.03);
  border-color: rgba(255, 255, 255, 0.15);
}

.placeholder-text {
  font-size: 1.05rem;
  line-height: 1.6;
  color: #666666;
  margin: 0;
}

.placeholder-box.dark .placeholder-text {
  color: #cccccc;
}

@media (max-width: 850px) {
  .story-container {
    grid-template-columns: 1fr;
    gap: 3rem;
  }

  .values-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .story-section,
  .values-section,
  .leadership-section,
  .sites-section {
    padding: 4.5rem 1.5rem;
  }
}

@media (max-width: 600px) {
  .about-hero {
    min-height: 440px;
    padding: calc(12vh + 30px) 1.25rem 3.5rem 1.25rem;
  }

  .values-grid {
    grid-template-columns: 1fr;
  }

  .story-section,
  .values-section,
  .leadership-section,
  .sites-section {
    padding: 3.5rem 1rem;
  }

  .site-card {
    flex-direction: column;
    gap: 1rem;
  }
}
</style>