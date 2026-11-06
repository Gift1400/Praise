<template>
  <div class="announcements-page">

  
    <section class="announcements-hero">
      <div class="hero-content">
        <p class="welcome-text">STAY INFORMED</p>
        <h1>Announcements</h1>
        <p>
          Important updates and news from Praise Church.
        </p>
      </div>
    </section>

   
    <section class="controls-section">
      <div class="controls-bar">
        <div class="filter-buttons">
          <button
            v-for="filter in filterOptions"
            :key="filter.value"
            :class="['filter-btn', { active: activeFilter === filter.value }]"
            @click="setFilter(filter.value)"
          >
            {{ filter.label }}
          </button>
        </div>
        <div class="controls-actions">
          <div class="announcement-count">
            <img
              src="https://cdn-icons-png.flaticon.com/512/1998/1998621.png"
              alt="Announcements"
              class="control-icon"
            />
            <span>{{ announcements.length }} {{ announcements.length === 1 ? 'Notice' : 'Notices' }}</span>
          </div>
          <button v-if="canManage" class="create-btn" @click="openCreateModal">
            <img
              src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png"
              alt="Add"
              class="btn-icon"
            />
            <span>New Announcement</span>
          </button>
        </div>
      </div>
    </section>

   
    <section v-if="loading" class="status-section">
      <p class="loading-text">Loading announcements...</p>
    </section>

   
    <section v-else-if="error" class="status-section">
      <p class="error-text">{{ error }}</p>
      <button class="retry-btn" @click="fetchAnnouncements">Retry</button>
    </section>

   
    <section v-else-if="announcements.length === 0" class="status-section">
      <p class="empty-text">No announcements found.</p>
    </section>

    
    <section v-else class="announcements-grid-section">
      <div class="announcements-grid">
        <article
          v-for="item in announcements"
          :key="item.announcementId || item.id"
          class="announcement-card"
        >
          <div class="card-header">
            <div class="card-header-left">
              <div class="megaphone-wrapper">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/1998/1998621.png"
                  alt="Announcement"
                  class="card-megaphone-icon"
                />
              </div>
              <span :class="['type-badge', getTypeBadgeClass(item)]">
                {{ formatType(item) }}
              </span>
            </div>

            <div class="header-right">
              <span v-if="getDate(item)" class="announcement-date">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/2693/2693507.png"
                  alt="Date"
                  class="date-icon"
                />
                {{ formatDate(getDate(item)) }}
              </span>
              <button
                v-if="canManage"
                class="card-delete-btn"
                title="Delete Announcement"
                @click="deleteAnnouncement(item)"
              >
                ✕
              </button>
            </div>
          </div>

          <div class="card-body">
            <h3 class="card-title">{{ item.title }}</h3>
            <p class="card-message">{{ getMessage(item) }}</p>
          </div>

          <div class="card-footer">
            <span class="praise-tag">Praise Church Bulletin</span>
          </div>
        </article>
      </div>
    </section>



    <div v-if="showCreateModal" class="modal-overlay" @click.self="showCreateModal = false">
      <div class="modal form-modal">
        <button class="modal-close" @click="showCreateModal = false">✕</button>
        <h2>New Church Announcement</h2>
        <form class="announcement-form" @submit.prevent="createAnnouncement">
          <div class="form-group">
            <label for="annTitle">Title</label>
            <input
              id="annTitle"
              v-model="form.title"
              type="text"
              placeholder="Announcement title"
              required
            />
          </div>
          <div class="form-group">
            <label for="annType">Category</label>
            <select id="annType" v-model="form.type" required>
              <option value="GENERAL">General</option>
              <option value="EVENT">Event</option>
              <option value="SUPPORT">Support</option>
            </select>
          </div>
          <div class="form-group">
            <label for="annMessage">Message</label>
            <textarea
              id="annMessage"
              v-model="form.message"
              rows="5"
              placeholder="Write the announcement details..."
              required
            ></textarea>
          </div>
          <div v-if="formError" class="form-error">{{ formError }}</div>
          <div class="modal-actions">
            <button type="button" class="cancel-btn" @click="showCreateModal = false">Cancel</button>
            <button type="submit" class="submit-btn" :disabled="submitting">
              {{ submitting ? 'Publishing...' : 'Publish Announcement' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getAuthHeader, isLeader } from '../util/auth'

const API_BASE = '/api/announcements'
const canManage = computed(() => isLeader())

const showCreateModal = ref(false)
const submitting = ref(false)
const formError = ref('')
const form = ref({
  title: '',
  type: 'GENERAL',
  message: ''
})

function openCreateModal() {
  form.value = {
    title: '',
    type: 'GENERAL',
    message: ''
  }
  formError.value = ''
  showCreateModal.value = true
}

async function createAnnouncement() {
  submitting.value = true
  formError.value = ''
  try {
    const payload = {
      announcementId: crypto.randomUUID(),
      title: form.value.title,
      type: form.value.type,
      message: form.value.message,
      date: new Date().toISOString().split('T')[0]
    }
    const res = await fetch(`${API_BASE}/create`, {
      method: 'POST',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)
    showCreateModal.value = false
    await fetchAnnouncements()
  } catch (err) {
    formError.value = err.message || 'Failed to create announcement'
  } finally {
    submitting.value = false
  }
}

async function deleteAnnouncement(item) {
  const id = item.announcementId || item.id
  if (!id) return
  if (!confirm(`Delete announcement "${item.title}"?`)) return
  try {
    const res = await fetch(`${API_BASE}/delete/${id}`, {
      method: 'DELETE',
      headers: { ...getAuthHeader() }
    })
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)
    await fetchAnnouncements()
  } catch (err) {
    alert(`Failed to delete: ${err.message}`)
  }
}

const filterOptions = [
  { label: 'All', value: 'ALL' },
  { label: 'Events', value: 'EVENTS' },
  { label: 'Support', value: 'SUPPORT' },
  { label: 'General', value: 'GENERAL' }
]

const announcements = ref([])
const loading = ref(false)
const error = ref('')
const activeFilter = ref('ALL')
 
function formatType(item) {
  const type = item.type || item.announcementType || 'GENERAL'
  return String(type).toUpperCase()
}

function getTypeBadgeClass(item) {
  const type = formatType(item)
  if (type.includes('EVENT')) return 'badge-events'
  if (type.includes('SUPPORT')) return 'badge-support'
  return 'badge-general'
}

function getDate(item) {
  return item.date || item.publishDate || item.createdAt || ''
}

function getMessage(item) {
  return item.message || item.content || item.description || ''
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
 
async function fetchAnnouncements() {
  loading.value = true
  error.value = ''
  try {
    const endpoint = activeFilter.value === 'ALL'
      ? `${API_BASE}/getAll`
      : `${API_BASE}/getByType/${activeFilter.value}`

    const res = await fetch(endpoint)
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)
    const data = await res.json()
    announcements.value = Array.isArray(data) ? data : []
  } catch (e) {
    error.value = `Failed to load announcements: ${e.message}`
  } finally {
    loading.value = false
  }
}

function setFilter(filter) {
  activeFilter.value = filter
  fetchAnnouncements()
}

// ── Lifecycle ──
onMounted(fetchAnnouncements)
</script>

<style scoped>

.announcements-page {
  min-height: 100vh;
  background: #ffffff;
  color: #222222;
  font-family: Arial, Helvetica, sans-serif;
}

.announcements-hero {
  min-height: 420px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 40px) 20px 60px;
  background:
    linear-gradient(rgba(0, 0, 0, 0.58), rgba(0, 0, 0, 0.58)),
    url("https://images.unsplash.com/photo-1529070538774-1843cb3265df?auto=format&fit=crop&w=1600&q=80");
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
  padding: 30px 7%;
  background: #dfdede;
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

.filter-buttons {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  
}

.filter-btn {
  padding: 10px 22px;
  border: 1px solid #cccccc;
  border-radius:0.5rem;
  background: #ffffff;
  color: #222222;
  font-weight: 600;
  font-size: 14px;
  cursor: pointer;
  transition: 0.3s ease;
}

.filter-btn:hover {
  border-color: #b08d57;
  color: #b08d57;
}

.filter-btn.active {
  background: #222222;
  color: #ffffff;
  border-color: #222222;
}

.announcement-count {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  font-weight: 600;
  color: #777777;
}

.control-icon {
  width: 18px;
  height: 18px;
  object-fit: contain;
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

.announcements-grid-section {
  padding: 50px 7%;
}

.announcements-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(340px, 1fr));
  gap: 25px;
  max-width: 1200px;
  margin: 0 auto;
}

.announcement-card {
  display: flex;
  flex-direction: column;
  background: #ffffff;
  transition: 0.3s ease;
  position: relative;
  overflow: hidden;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.03);
}

.announcement-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
  border-color: #d5d5d5;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 18px 20px 12px;
  border-bottom: 1px solid #f2f2f2;
  gap: 10px;
}

.card-header-left {
  display: flex;
  align-items: center;
  gap: 10px;
}

.megaphone-wrapper {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: center;
}

.card-megaphone-icon {
  width: 16px;
  height: 16px;
  object-fit: contain;
}

.type-badge {
  display: inline-block;
  padding: 4px 10px;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.8px;
  text-transform: uppercase;
  border-radius: 3px;
}

.badge-events {
  background: rgba(33, 150, 243, 0.12);
  color: #1565c0;
  border: 1px solid rgba(21, 101, 192, 0.25);
}

.badge-support {
  background: rgba(46, 125, 50, 0.12);
  color: #2e7d32;
  border: 1px solid rgba(46, 125, 50, 0.25);
}

.badge-general {
  background: rgba(176, 141, 87, 0.15);
  color: #9b6d00;
  border: 1px solid rgba(176, 141, 87, 0.3);
}

.announcement-date {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  color: #888888;
  font-weight: 500;
  white-space: nowrap;
}

.date-icon {
  width: 13px;
  height: 13px;
  object-fit: contain;
  opacity: 0.7;
}

.card-body {
  flex: 1;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  border:1px solid gray;
  border-radius:1rem;
  background-color:rgb(224, 224, 224);
}

.card-title {
  font-size: 20px;
  font-weight: 700;
  color: #222222;
  margin: 0;
  line-height: 1.35;
}

.card-message {
  font-size: 14px;
  color: #555555;
  line-height: 1.7;
  margin: 0;
  white-space: pre-line;
}

.card-footer {
  padding: 12px 20px;
  background: #fafafa;
  border-top: 1px solid #f0f0f0;
}

.praise-tag {
  font-size: 11px;
  font-weight: bold;
  letter-spacing: 1px;
  text-transform: uppercase;
  color: #b08d57;
}

@media (max-width: 900px) {
  .announcements-grid {
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

  .filter-buttons {
    justify-content: center;
  }

  .announcement-count {
    justify-content: center;
  }

  .announcements-grid {
    grid-template-columns: 1fr;
  }

  .card-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
  }
}

.controls-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.create-btn {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  background: #9b6d00;
  color: #fff;
  border: none;
  padding: 10px 18px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s, transform 0.2s;
}

.create-btn:hover {
  background: #80633e;
  transform: translateY(-1px);
}

.btn-icon {
  width: 14px;
  height: 14px;
  filter: brightness(0) invert(1);
}

.header-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.card-delete-btn {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 1px solid #e0dcd5;
  background: #ffffff;
  color: #8c8273;
  font-size: 12px;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s;
}

.card-delete-btn:hover {
  background: #fee2e2;
  border-color: #fca5a5;
  color: #b91c1c;
}

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.6);
  backdrop-filter: blur(4px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  padding: 20px;
}

.form-modal {
  background: #ffffff;
  border-radius: 16px;
  width: 100%;
  max-width: 520px;
  padding: 32px;
  position: relative;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
}

.modal-close {
  position: absolute;
  top: 20px;
  right: 20px;
  background: transparent;
  border: none;
  font-size: 18px;
  color: #888;
  cursor: pointer;
}

.form-modal h2 {
  font-family: Georgia, serif;
  font-size: 24px;
  color: #2f281f;
  margin: 0 0 20px;
}

.announcement-form .form-group {
  margin-bottom: 18px;
}

.announcement-form label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 6px;
}

.announcement-form input,
.announcement-form select,
.announcement-form textarea {
  width: 100%;
  padding: 10px 14px;
  border: 1px solid #ddd8cf;
  border-radius: 8px;
  font-size: 14px;
  font-family: inherit;
  box-sizing: border-box;
}

.announcement-form input:focus,
.announcement-form select:focus,
.announcement-form textarea:focus {
  outline: none;
  border-color: #9b6d00;
}

.form-error {
  color: #b91c1c;
  background: #fdf2f2;
  padding: 8px 12px;
  border-radius: 6px;
  font-size: 13px;
  margin-bottom: 16px;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 24px;
}

.cancel-btn {
  background: #f3efe9;
  border: none;
  padding: 10px 18px;
  border-radius: 8px;
  font-size: 14px;
  color: #555;
  cursor: pointer;
}

.submit-btn {
  background: #9b6d00;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

.submit-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

</style>