<template>
  <div class="sermons-page">

   <section class="sermons-hero">
      <div class="hero-content">
        <p class="welcome-text">THE WORD OF GOD</p>
        <h1>Sermons</h1>
        <p>
          Explore messages that inspire, challenge, and transform.
        </p>
      </div>
    </section>

    <section class="controls-section">
      <div class="controls-bar">
        <div class="info-tag">
          <img
            src="https://cdn-icons-png.flaticon.com/512/4289/4289857.png"
            alt="Microphone"
            class="control-icon"
          />
          <span class="info-text">Latest Messages & Teachings</span>
        </div>
        <div class="search-box">
          <input
            v-model="searchQuery"
            type="text"
            placeholder="Search sermons by title or speaker..."
            class="search-input"
          />
        </div>
      </div>
    </section>

    <section v-if="loading" class="status-section">
      <p class="loading-text">Loading sermons...</p>
    </section>

    <section v-else-if="error" class="status-section">
      <p class="error-text">{{ error }}</p>
      <button class="retry-btn" @click="fetchSermons">Retry</button>
    </section>

   <section v-else-if="filteredSermons.length === 0" class="status-section">
      <p class="empty-text">No sermons found.</p>
    </section>

    <section v-else class="sermons-grid-section">
      <div class="sermons-grid">
        <article
          v-for="sermon in filteredSermons"
          :key="sermon.sermonId || sermon.id"
          class="sermon-card"
          @click="openDetailModal(sermon)"
        >
          <div class="card-top-bar">
            <div class="sermon-icon-wrapper">
              <img
                src="https://cdn-icons-png.flaticon.com/512/4289/4289857.png"
                alt="Sermon"
                class="sermon-mic-icon"
              />
            </div>
            <span v-if="sermon.date" class="sermon-date-badge">
              <img
                src="https://cdn-icons-png.flaticon.com/512/2693/2693507.png"
                alt="Date"
                class="badge-icon"
              />
              {{ formatDate(sermon.date) }}
            </span>
          </div>

          <div class="card-body">
            <h3 class="card-title">{{ sermon.title }}</h3>
            <p class="card-description">{{ truncate(sermon.description, 130) }}</p>
          </div>

          <div class="card-footer">
            <div v-if="getLeaderName(sermon)" class="sermon-leader">
              <img
                src="https://cdn-icons-png.flaticon.com/512/681/681494.png"
                alt="Speaker"
                class="footer-icon"
              />
              <span class="leader-name">{{ getLeaderName(sermon) }}</span>
            </div>
            <span class="listen-link">Read Details &rarr;</span>
          </div>
        </article>
      </div>
    </section>

    <div v-if="showDetailModal" class="modal-overlay" @click.self="showDetailModal = false">
      <div class="modal detail-modal">
        <button class="modal-close" @click="showDetailModal = false">✕</button>

        <div class="detail-header">
          <div class="detail-badge">
            <img
              src="https://cdn-icons-png.flaticon.com/512/4289/4289857.png"
              alt="Sermon"
              class="modal-badge-icon"
            />
            <span>Sermon Message</span>
          </div>
          <h2>{{ selectedSermon?.title }}</h2>
        </div>

        <div class="detail-body">
          <div class="detail-meta-row">
            <div v-if="selectedSermon?.date" class="detail-meta-item">
              <img
                src="https://cdn-icons-png.flaticon.com/512/2693/2693507.png"
                alt="Date"
                class="detail-icon"
              />
              <div>
                <span class="detail-meta-label">Date</span>
                <span class="detail-meta-val">{{ formatDate(selectedSermon.date) }}</span>
              </div>
            </div>

            <div v-if="getLeaderName(selectedSermon)" class="detail-meta-item">
              <img
                src="https://cdn-icons-png.flaticon.com/512/681/681494.png"
                alt="Speaker"
                class="detail-icon"
              />
              <div>
                <span class="detail-meta-label">Speaker / Minister</span>
                <span class="detail-meta-val">{{ getLeaderName(selectedSermon) }}</span>
              </div>
            </div>
          </div>

          <div class="detail-message-container">
            <h4 class="notes-heading">Message Notes & Reflection</h4>
            <p class="detail-description">{{ selectedSermon?.description }}</p>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'

const API_ENDPOINT = '/api/sermon/getAll'


const sermons = ref([])
const loading = ref(false)
const error = ref('')
const searchQuery = ref('')


const showDetailModal = ref(false)
const selectedSermon = ref(null)

function getLeaderName(sermon) {
  if (!sermon?.leader) return ''
  if (typeof sermon.leader === 'string') return sermon.leader
  const first = sermon.leader.firstName || sermon.leader.name || ''
  const last = sermon.leader.lastName || ''
  const full = `${first} ${last}`.trim()
  return full || sermon.leader.leaderName || 'Church Leader'
}

function formatDate(dateStr) {
  if (!dateStr) return ''
  const d = new Date(dateStr)
  if (isNaN(d.getTime())) return dateStr
  return d.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric'
  })
}

function truncate(text, max = 130) {
  if (!text) return ''
  return text.length > max ? text.substring(0, max) + '…' : text
}

const filteredSermons = computed(() => {
  if (!searchQuery.value.trim()) return sermons.value
  const query = searchQuery.value.toLowerCase()
  return sermons.value.filter(sermon => {
    const titleMatch = sermon.title?.toLowerCase().includes(query)
    const descMatch = sermon.description?.toLowerCase().includes(query)
    const leaderMatch = getLeaderName(sermon).toLowerCase().includes(query)
    return titleMatch || descMatch || leaderMatch
  })
})


async function fetchSermons() {
  loading.value = true
  error.value = ''
  try {
    const res = await fetch(API_ENDPOINT)
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)
    const data = await res.json()
    sermons.value = Array.isArray(data) ? data : []
  } catch (e) {
    error.value = `Failed to load sermons: ${e.message}`
  } finally {
    loading.value = false
  }
}


function openDetailModal(sermon) {
  selectedSermon.value = sermon
  showDetailModal.value = true
}

// ── Lifecycle ──
onMounted(fetchSermons)
</script>

<style scoped>
.sermons-page {
  min-height: 100vh;
  background: #ffffff;
  color: #222222;
  font-family: Arial, Helvetica, sans-serif;
}

.sermons-hero {
  min-height: 420px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 40px) 20px 60px;
  background:
    linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
    url("https://images.unsplash.com/photo-1504052434569-70ad5836ab65?auto=format&fit=crop&w=1600&q=80");
  background-size: cover;
  background-position: center;
}

.hero-content {
  max-width: 750px;
  color: #ffffff;
}

.welcome-text {
  font-size: 14px;
  letter-spacing: 3px;
  font-weight: bold;
  margin-bottom: 15px;
  color: #b08d57;
}

.hero-content h1 {
  font-size: 48px;
  margin-bottom: 20px;
}

.hero-content p:last-child {
  font-size: 18px;
  line-height: 1.7;
}

.controls-section {
  padding: 25px 7%;
  background: #f7f7f7;
  border-bottom: 1px solid #e5e5e5;
}

.controls-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  flex-wrap: wrap;
  gap: 15px;
}

.info-tag {
  display: inline-flex;
  align-items: center;
  gap: 10px;
}

.control-icon {
  width: 20px;
  height: 20px;
  object-fit: contain;
}

.info-text {
  font-size: 15px;
  font-weight: 600;
  color: #222222;
  letter-spacing: 0.5px;
}

.search-box {
  flex: 1;
  max-width: 420px;
  
}

.search-input {
  width: 100%;
  padding: 10px 16px;
  border: 1px solid #cccccc;
  border-radius:0.5rem;
  background: #ffffff;
  color: #222222;
  font-family: inherit;
  font-size: 14px;
  outline: none;
  transition: border-color 0.2s ease;
}

.search-input:focus {
  border-color: #b08d57;
}


.status-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 80px 20px;
  gap: 15px;
}

.loading-text {
  font-size: 18px;
  color: #888888;
}

.error-text {
  font-size: 16px;
  color: #c0392b;
}

.retry-btn {
  padding: 10px 24px;
  border: 1px solid #c0392b;
  background: transparent;
  color: #c0392b;
  font-weight: bold;
  cursor: pointer;
  transition: 0.3s ease;
}

.retry-btn:hover {
  background: #c0392b;
  color: #ffffff;
}

.empty-text {
  font-size: 18px;
  color: #888888;
}


.sermons-grid-section {
  padding: 50px 7%;
}

.sermons-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(340px, 1fr));
  gap: 25px;
  max-width: 1200px;
  margin: 0 auto;
}


.sermon-card {
  display: flex;
  flex-direction: column;
  border: 1px solid #e5e5e5;
  background: #ffffff;
  transition: 0.3s ease;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-top: 3px solid #b08d57;
}

.sermon-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.08);
  border-color: #b08d57;
}

.card-top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px 10px;
}

.sermon-icon-wrapper {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background: rgba(176, 141, 87, 0.12);
  display: flex;
  align-items: center;
  justify-content: center;
}

.sermon-mic-icon {
  width: 18px;
  height: 18px;
  object-fit: contain;
}

.sermon-date-badge {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  color: #777777;
  font-weight: 500;
}

.badge-icon {
  width: 13px;
  height: 13px;
  object-fit: contain;
  opacity: 0.7;
}

.card-body {
  flex: 1;
  padding: 10px 20px 15px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.card-title {
  font-size: 19px;
  font-weight: 700;
  color: #222222;
  margin: 0;
  line-height: 1.35;
}

.card-description {
  font-size: 14px;
  color: #666666;
  line-height: 1.6;
  margin: 0;
}

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 14px 20px;
  background: #fafafa;
  border-top: 1px solid #f0f0f0;
}

.sermon-leader {
  display: inline-flex;
  align-items: center;
  gap: 7px;
  font-size: 13px;
  color: #555555;
  font-weight: 600;
}

.footer-icon {
  width: 15px;
  height: 15px;
  object-fit: contain;
  opacity: 0.75;
}

.leader-name {
  max-width: 180px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.listen-link {
  font-size: 13px;
  font-weight: bold;
  color: #b08d57;
  letter-spacing: 0.3px;
}

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.65);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 200;
  padding: 20px;
}

.modal {
  background: #ffffff;
  width: 100%;
  max-height: 90vh;
  overflow-y: auto;
  position: relative;
  padding: 40px;
}

.detail-modal {
  max-width: 650px;
}

.modal-close {
  position: absolute;
  top: 15px;
  right: 20px;
  background: none;
  border: none;
  font-size: 22px;
  cursor: pointer;
  color: #888888;
  transition: color 0.2s;
}

.modal-close:hover {
  color: #222222;
}

.detail-header {
  margin-bottom: 25px;
  padding-bottom: 15px;
  border-bottom: 3px solid #b08d57;
}

.detail-badge {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  font-weight: bold;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  color: #b08d57;
  margin-bottom: 10px;
}

.modal-badge-icon {
  width: 15px;
  height: 15px;
  object-fit: contain;
}

.detail-header h2 {
  font-size: 28px;
  color: #222222;
  margin: 0;
  line-height: 1.3;
}

.detail-meta-row {
  display: flex;
  flex-wrap: wrap;
  gap: 25px;
  padding: 15px 18px;
  background: #f9f9f9;
  border-left: 3px solid #222222;
  margin-bottom: 25px;
}

.detail-meta-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.detail-icon {
  width: 18px;
  height: 18px;
  object-fit: contain;
  opacity: 0.75;
}

.detail-meta-label {
  display: block;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
  color: #888888;
}

.detail-meta-val {
  display: block;
  font-size: 14px;
  font-weight: 600;
  color: #222222;
}

.notes-heading {
  font-size: 14px;
  font-weight: bold;
  letter-spacing: 1px;
  text-transform: uppercase;
  color: #888888;
  margin-bottom: 12px;
}

.detail-description {
  color: #444444;
  line-height: 1.8;
  font-size: 15px;
  white-space: pre-line;
}

/* ── Responsive ── */
@media (max-width: 900px) {
  .sermons-grid {
    grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  }
}

@media (max-width: 600px) {
  .hero-content h1 {
    font-size: 34px;
  }

  .controls-bar {
    flex-direction: column;
    align-items: stretch;
  }

  .search-box {
    max-width: 100%;
  }

  .sermons-grid {
    grid-template-columns: 1fr;
  }

  .modal {
    padding: 25px;
  }

  .detail-meta-row {
    flex-direction: column;
    gap: 15px;
  }
}
</style>