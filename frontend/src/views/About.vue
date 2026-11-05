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