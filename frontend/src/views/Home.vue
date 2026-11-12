<template>
  <div class="home">
    <!-- Hero Section-->
    <section class="hero-section">
      <section class="hero-overlay">
        <h2 id="hero-title">Welcome Home</h2>
        <p id="des-hero">Meet the holy and the warm hands of the Praise community at Holy Trinity</p>
        <article class="overlay-bts">
          <button id="bt-plan-visit" @click="navigateTo('/contacts')">Plan Your Visit</button>
          <button id="bt-sermons" @click="navigateTo('/sermons')">Watch Sermons</button>
        </article>
      </section>
    </section>

    <!-- 2. Upcoming Events Section -->
    <section class="events-section">
      <div class="section-container">
        <div class="section-header">
          <span class="sub-heading">FELLOWSHIP & ACTIVITIES</span>
          <h2 class="section-title">Upcoming Events</h2>
          <div class="gold-divider center"></div>
          <p class="section-subtitle">Connect, worship, and grow with our church family at these upcoming gatherings.
          </p>
        </div>

        <div v-if="eventsLoading" class="loading-state">
          <p>Loading upcoming events...</p>
        </div>

        <div v-else-if="events.length === 0" class="empty-state">
          <p>No upcoming events</p>
        </div>

        <div v-else class="events-grid">
          <article v-for="event in events" :key="event.eventId || event.id" class="event-card">
            <div class="event-date-badge">
              <span class="date-day">{{ formatDay(event.date) }}</span>
              <span class="date-month">{{ formatMonth(event.date) }}</span>
            </div>
            <div class="event-details">
              <h3 class="event-title">{{ event.title }}</h3>
              <div class="event-meta">
                <div class="meta-row" v-if="event.time">
                  <img src="https://cdn-icons-png.flaticon.com/512/2088/2088617.png" alt="Time icon"
                    class="meta-icon" />
                  <span>{{ event.time }}</span>
                </div>
                <div class="meta-row" v-if="event.location">
                  <img src="https://cdn-icons-png.flaticon.com/512/684/684809.png" alt="Location icon"
                    class="meta-icon" />
                  <span>{{ event.location }}</span>
                </div>
              </div>
            </div>
          </article>
        </div>

        <div class="section-action">
          <router-link to="/events" class="btn-outline">View All Events</router-link>
        </div>
      </div>
    </section>

    <!-- 3. Latest Announcements Section -->
    <section class="announcements-section">
      <div class="section-container">
        <div class="section-header dark">
          <span class="sub-heading gold">STAY INFORMED</span>
          <h2 class="section-title white">Announcements</h2>
          <div class="gold-divider center"></div>
          <p class="section-subtitle muted">Important notices, ministry updates, and announcements from Praise Church.
          </p>
        </div>

        <div v-if="announcementsLoading" class="loading-state dark">
          <p>Loading announcements...</p>
        </div>

        <div v-else-if="announcements.length === 0" class="empty-state dark">
          <p>No announcements right now.</p>
        </div>

        <div v-else class="announcements-grid">
          <article v-for="item in announcements" :key="item.id || item.announcementId" class="announcement-card">
            <div class="announcement-top">
              <span class="announcement-badge">{{ item.type || item.category || 'General' }}</span>
              <span class="announcement-date" v-if="item.date || item.createdAt">
                {{ formatDate(item.date || item.createdAt) }}
              </span>
            </div>
            <h3 class="announcement-title">{{ item.title }}</h3>
            <p class="announcement-preview">
              {{ truncate(item.message || item.content || item.description || '', 120) }}
            </p>
          </article>
        </div>

        <div class="section-action">
          <router-link to="/announcements" class="btn-gold">View All</router-link>
        </div>
      </div>
    </section>

    <!-- 4. Bible Verse Section -->
    <section class="verse-section">
      <div class="verse-container">
        <div class="verse-icon-wrapper">
          <img src="https://cdn-icons-png.flaticon.com/512/27/27122.png" alt="Scripture quotation"
            class="verse-quote-icon" />
        </div>
        <blockquote class="verse-text">
          &ldquo;{{ verse.text }}&rdquo;
        </blockquote>
        <div class="verse-ref-wrapper">
          <div class="gold-line"></div>
          <cite class="verse-reference">{{ verse.reference }}</cite>
          <div class="gold-line"></div>
        </div>
      </div>
    </section>

    <!-- 5. Call to Action Section -->
    <section class="cta-section">
      <div class="section-container cta-container">
        <span class="sub-heading gold">COME AS YOU ARE</span>
        <h2 class="cta-title">Join Our Community</h2>
        <p class="cta-subtitle">
          Experience warm fellowship, inspiring worship, and God's transforming grace. We look forward to meeting you.
        </p>
        <div class="cta-actions">
          <router-link to="/contacts" class="btn-cta-primary">Plan Your Visit</router-link>
          <router-link to="/about" class="btn-cta-secondary">Get Connected</router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const gatheringImg = new URL("../assets/images/Gathering.jpeg", import.meta.url).href

const events = ref([])
const eventsLoading = ref(true)

const announcements = ref([])
const announcementsLoading = ref(true)

const verse = ref({
  text: 'For where two or three gather in my name, there am I with them.',
  reference: 'Matthew 18:20'
})

function navigateTo(path) {
  router.push(path)
}

function formatDay(dateStr) {
  if (!dateStr) return '--'
  const d = new Date(dateStr)
  return isNaN(d.getTime()) ? '--' : d.getDate()
}

function formatMonth(dateStr) {
  if (!dateStr) return '---'
  const d = new Date(dateStr)
  return isNaN(d.getTime()) ? '---' : d.toLocaleString('en-US', { month: 'short' }).toUpperCase()
}

function formatDate(dateStr) {
  if (!dateStr) return ''
  const d = new Date(dateStr)
  return isNaN(d.getTime()) ? dateStr : d.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' })
}

function truncate(text, max = 120) {
  if (!text) return ''
  return text.length > max ? text.slice(0, max) + '…' : text
}

async function fetchUpcomingEvents() {
  try {
    const res = await fetch('/api/events/upcoming')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data)) {
        events.value = data.slice(0, 3)
      }
    }
  } catch (err) {
    console.error('Error fetching upcoming events:', err)
  } finally {
    eventsLoading.value = false
  }
}

async function fetchAnnouncements() {
  try {
    const res = await fetch('/api/announcements/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data)) {
        announcements.value = data.slice(0, 3)
      }
    }
  } catch (err) {
    console.error('Error fetching announcements:', err)
  } finally {
    announcementsLoading.value = false
  }
}

async function fetchBibleVerse() {
  try {
    const res = await fetch('/api/bibleVerse/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data) && data.length > 0) {
        const item = data[Math.floor(Math.random() * data.length)]
        const text = item.text || item.verseText || item.scriptureText || item.content || item.quote
        let reference = item.reference
        if (!reference && item.book) {
          const chap = item.chapter ? ` ${item.chapter}` : ''
          const ver = item.verseNumber || item.verse ? `:${item.verseNumber || item.verse}` : ''
          reference = `${item.book}${chap}${ver}`
        }
        if (text) {
          verse.value = {
            text: text.trim(),
            reference: reference || 'Holy Scripture'
          }
        }
      }
    }
  } catch (err) {
    console.error('Error fetching bible verse:', err)
  }
}

onMounted(() => {
  fetchUpcomingEvents()
  fetchAnnouncements()
  fetchBibleVerse()
})
</script>

<style src="../styles/home.css"></style>

<style scoped>
.section-container {
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
}

.section-header {
  text-align: center;
  margin-bottom: 3rem;
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
  font-size: clamp(2rem, 4vw, 2.8rem);
  font-weight: 700;
  color: #1a1a2e;
  margin: 0 0 1rem 0;
  line-height: 1.2;
}

.section-title.white {
  color: #ffffff;
}

.gold-divider {
  width: 60px;
  height: 3px;
  background-color: #b08d57;
  margin: 0.5rem 0 1.5rem 0;
  border-radius: 2px;
}

.gold-divider.center {
  margin: 0.5rem auto 1.5rem auto;
}

.section-subtitle {
  font-size: 1.05rem;
  color: #666666;
  max-width: 600px;
  margin: 0 auto;
  line-height: 1.6;
}

.section-subtitle.muted {
  color: #b3b3b3;
}

.section-action {
  text-align: center;
  margin-top: 3rem;
}


.welcome-section {
  background-color: #ffffff;
  padding: 6rem 2rem;
}

.welcome-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 4rem;
  align-items: center;
}

.welcome-text-col {
  display: flex;
  flex-direction: column;
}

.welcome-desc {
  font-size: 1.1rem;
  line-height: 1.8;
  color: #444444;
  margin: 0 0 1.25rem 0;
}

.welcome-desc.secondary {
  color: #666666;
  font-size: 1rem;
}

.welcome-action {
  margin-top: 1.5rem;
}

.btn-primary {
  display: inline-block;
  background-color: #b08d57;
  color: #ffffff;
  padding: 0.85rem 2rem;
  border-radius: 30px;
  font-size: 1rem;
  font-weight: 600;
  text-decoration: none;
  transition: background-color 0.25s ease, transform 0.2s ease, box-shadow 0.25s ease;
  box-shadow: 0 4px 15px rgba(176, 141, 87, 0.3);
}

.btn-primary:hover {
  background-color: #9b6d00;
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(176, 141, 87, 0.4);
}

.welcome-image-col {
  display: flex;
  justify-content: center;
}

.image-wrapper {
  position: relative;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
  max-height: 480px;
  width: 100%;
}

.welcome-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.5s ease;
}

.image-wrapper:hover .welcome-img {
  transform: scale(1.03);
}


.events-section {
  background-color: #f7f6f2;
  padding: 6rem 2rem;
}

.events-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
}

.event-card {
  background-color: #ffffff;
  border-radius: 16px;
  padding: 2rem;
  display: flex;
  gap: 1.5rem;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.06);
  border: 1px solid rgba(176, 141, 87, 0.15);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.event-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 14px 30px rgba(0, 0, 0, 0.12);
  border-color: #b08d57;
}

.event-date-badge {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 65px;
  height: 75px;
  background: linear-gradient(135deg, #b08d57, #9b6d00);
  color: #ffffff;
  border-radius: 12px;
  flex-shrink: 0;
  padding: 0.5rem;
}

.date-day {
  font-size: 1.6rem;
  font-weight: 700;
  line-height: 1;
}

.date-month {
  font-size: 0.75rem;
  font-weight: 600;
  letter-spacing: 1px;
  margin-top: 4px;
}

.event-details {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 0.6rem;
}

.event-title {
  font-size: 1.25rem;
  font-weight: 600;
  color: #1a1a2e;
  margin: 0;
  line-height: 1.3;
}

.event-meta {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.meta-row {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  font-size: 0.9rem;
  color: #666666;
}

.meta-icon {
  width: 15px;
  height: 15px;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
  flex-shrink: 0;
}

.btn-outline {
  display: inline-block;
  border: 2px solid #b08d57;
  color: #b08d57;
  background-color: transparent;
  padding: 0.8rem 2.2rem;
  border-radius: 30px;
  font-size: 1rem;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.25s ease;
}

.btn-outline:hover {
  background-color: #b08d57;
  color: #ffffff;
  transform: translateY(-2px);
}


.announcements-section {
  background-color: #16213e;
  padding: 6rem 2rem;
}

.announcements-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
}

.announcement-card {
  background-color: rgba(255, 255, 255, 0.04);
  border: 1px solid rgba(255, 255, 255, 0.1);
  border-radius: 16px;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  transition: all 0.3s ease;
}

.announcement-card:hover {
  background-color: rgba(255, 255, 255, 0.08);
  border-color: #e3901c;
  transform: translateY(-4px);
}

.announcement-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.announcement-badge {
  background-color: rgba(227, 144, 28, 0.2);
  color: #e3901c;
  padding: 0.35rem 0.85rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

.announcement-date {
  font-size: 0.82rem;
  color: #999999;
}

.announcement-title {
  color: #ffffff;
  font-size: 1.25rem;
  font-weight: 600;
  margin: 0;
  line-height: 1.35;
}

.announcement-preview {
  color: #cccccc;
  font-size: 0.95rem;
  line-height: 1.6;
  margin: 0;
}

.btn-gold {
  display: inline-block;
  background-color: #e3901c;
  color: #ffffff;
  padding: 0.85rem 2.2rem;
  border-radius: 30px;
  font-size: 1rem;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.25s ease;
}

.btn-gold:hover {
  background-color: #c77b14;
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(227, 144, 28, 0.4);
}


.verse-section {
  background-color: #faf7f2;
  padding: 6rem 2rem;
  text-align: center;
}

.verse-container {
  max-width: 800px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.verse-icon-wrapper {
  margin-bottom: 1.5rem;
}

.verse-quote-icon {
  width: 36px;
  height: 36px;
  opacity: 0.7;
  filter: brightness(0) saturate(100%) invert(67%) sepia(21%) saturate(940%) hue-rotate(359deg) brightness(88%) contrast(83%);
}

.verse-text {
  font-family: Georgia, 'Times New Roman', serif;
  font-size: clamp(1.4rem, 2.5vw, 1.9rem);
  font-style: italic;
  line-height: 1.7;
  color: #222222;
  margin: 0 0 2rem 0;
}

.verse-ref-wrapper {
  display: flex;
  align-items: center;
  gap: 1.2rem;
}

.gold-line {
  width: 50px;
  height: 2px;
  background-color: #b08d57;
}

.verse-reference {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 1rem;
  font-style: normal;
  font-weight: 700;
  color: #b08d57;
  letter-spacing: 1.5px;
  text-transform: uppercase;
}


.cta-section {
  background: linear-gradient(135deg, #111111 0%, #1a1a2e 100%);
  color: #ffffff;
  padding: 6rem 2rem;
  text-align: center;
}

.cta-container {
  max-width: 750px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.cta-title {
  font-size: clamp(2.2rem, 4.5vw, 3.2rem);
  font-weight: 700;
  margin: 0.5rem 0 1.25rem 0;
  line-height: 1.2;
}

.cta-subtitle {
  font-size: 1.15rem;
  color: #d1d1d1;
  line-height: 1.7;
  margin: 0 0 2.5rem 0;
}

.cta-actions {
  display: flex;
  gap: 1.5rem;
  flex-wrap: wrap;
  justify-content: center;
}

.btn-cta-primary {
  background-color: #e3901c;
  color: #ffffff;
  padding: 0.95rem 2.2rem;
  border-radius: 30px;
  font-size: 1.05rem;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.25s ease;
  box-shadow: 0 6px 20px rgba(227, 144, 28, 0.35);
}

.btn-cta-primary:hover {
  background-color: #c77b14;
  transform: translateY(-2px);
  box-shadow: 0 8px 24px rgba(227, 144, 28, 0.45);
}

.btn-cta-secondary {
  border: 2px solid #ffffff;
  background-color: transparent;
  color: #ffffff;
  padding: 0.95rem 2.2rem;
  border-radius: 30px;
  font-size: 1.05rem;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.25s ease;
}

.btn-cta-secondary:hover {
  background-color: rgba(255, 255, 255, 0.15);
  transform: translateY(-2px);
}


.loading-state,
.empty-state {
  text-align: center;
  padding: 3rem 1rem;
  color: #666666;
  font-size: 1.05rem;
}

.loading-state.dark,
.empty-state.dark {
  color: #aaaaaa;
}


@media (max-width: 850px) {
  .welcome-container {
    grid-template-columns: 1fr;
    gap: 3rem;
  }

  .events-grid,
  .announcements-grid {
    grid-template-columns: 1fr;
  }

  .welcome-section,
  .events-section,
  .announcements-section,
  .verse-section,
  .cta-section {
    padding: 4.5rem 1.5rem;
  }
}

@media (max-width: 600px) {

  .welcome-section,
  .events-section,
  .announcements-section,
  .verse-section,
  .cta-section {
    padding: 3.5rem 1rem;
  }

  .event-card {
    padding: 1.25rem;
    gap: 1rem;
    background-color: rgba(0, 0, 0, 0.175);
  }

  .event-date-badge {
    width: 55px;
    height: 65px;
  }

  .date-day {
    font-size: 1.35rem;
  }

  .cta-actions {
    flex-direction: column;
    width: 100%;
  }

  .btn-cta-primary,
  .btn-cta-secondary {
    width: 100%;
    text-align: center;
  }
}
</style>
