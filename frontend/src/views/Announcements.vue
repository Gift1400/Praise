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