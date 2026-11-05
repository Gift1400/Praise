<template>
  <div class="events-page">

    <!-- Hero Section -->
    <section class="events-hero">
      <div class="hero-content">
        <p class="welcome-text">JOIN US IN FELLOWSHIP</p>
        <h1>Church Events</h1>
        <p>
          Stay connected with upcoming gatherings, community outreach, worship
          nights and everything happening across Praise Church.
        </p>
      </div>
    </section>

    <!-- Controls Bar -->
    <section class="controls-section">
      <div class="controls-bar">
        <div class="filter-buttons">
          <button
            :class="['filter-btn', { active: activeFilter === 'all' }]"
            @click="setFilter('all')"
          >
            All Events
          </button>
          <button
            :class="['filter-btn', { active: activeFilter === 'upcoming' }]"
            @click="setFilter('upcoming')"
          >
            Upcoming
          </button>
        </div>
        <button class="create-btn" @click="openCreateModal">+ New Event</button>
      </div>
    </section>

    <!-- Loading State -->
    <section v-if="loading" class="status-section">
      <p class="loading-text">Loading events...</p>
    </section>

    <!-- Error State -->
    <section v-else-if="error" class="status-section">
      <p class="error-text">{{ error }}</p>
      <button class="retry-btn" @click="fetchEvents">Retry</button>
    </section>

    <!-- Empty State -->
    <section v-else-if="events.length === 0" class="status-section">
      <p class="empty-text">No events found.</p>
    </section>

    <!-- Event Cards Grid -->
    <section v-else class="events-grid-section">
      <div class="events-grid">
        <article
          v-for="event in events"
          :key="event.eventId"
          class="event-card"
          @click="openDetailModal(event)"
        >
          <div class="card-date">
            <span class="date-day">{{ formatDay(event.date) }}</span>
            <span class="date-month">{{ formatMonth(event.date) }}</span>
          </div>
          <div class="card-body">
            <h3 class="card-title">{{ event.title }}</h3>
            <p class="card-description">{{ truncate(event.description, 100) }}</p>
            <div class="card-meta">
              <span class="meta-item">📍 {{ event.location }}</span>
              <span class="meta-item">🕐 {{ event.time }}</span>
            </div>
            <div class="card-footer">
              <span class="attendees">👥 {{ event.expectedAttendees }} expected</span>
              <span
                v-if="event.churchSite"
                class="church-site"
              >
                ⛪ {{ event.churchSite.churchName }}
              </span>
            </div>
          </div>
          <div class="card-actions" @click.stop>
            <button class="action-btn edit-btn" @click="openEditModal(event)" title="Edit">✏️</button>
            <button class="action-btn delete-btn" @click="confirmDelete(event)" title="Delete">🗑️</button>
          </div>
        </article>
      </div>
    </section>

    <!-- Detail Modal -->
    <div v-if="showDetailModal" class="modal-overlay" @click.self="showDetailModal = false">
      <div class="modal detail-modal">
        <button class="modal-close" @click="showDetailModal = false">✕</button>
        <div class="detail-header">
          <h2>{{ selectedEvent?.title }}</h2>
        </div>
        <div class="detail-body">
          <p class="detail-description">{{ selectedEvent?.description }}</p>
          <div class="detail-info-grid">
            <div class="detail-info-item">
              <span class="detail-label">Date</span>
              <span class="detail-value">{{ selectedEvent?.date }}</span>
            </div>
            <div class="detail-info-item">
              <span class="detail-label">Time</span>
              <span class="detail-value">{{ selectedEvent?.time }}</span>
            </div>
            <div class="detail-info-item">
              <span class="detail-label">Location</span>
              <span class="detail-value">{{ selectedEvent?.location }}</span>
            </div>
            <div class="detail-info-item">
              <span class="detail-label">Expected Attendees</span>
              <span class="detail-value">{{ selectedEvent?.expectedAttendees }}</span>
            </div>
            <div v-if="selectedEvent?.churchSite" class="detail-info-item">
              <span class="detail-label">Church Site</span>
              <span class="detail-value">{{ selectedEvent?.churchSite?.churchName }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Create / Edit Modal -->
    <div v-if="showFormModal" class="modal-overlay" @click.self="closeFormModal">
      <div class="modal form-modal">
        <button class="modal-close" @click="closeFormModal">✕</button>
        <h2>{{ isEditing ? 'Edit Event' : 'Create Event' }}</h2>

        <form class="event-form" @submit.prevent="submitForm">
          <div class="form-row">
            <div class="form-group">
              <label for="eventTitle">Title</label>
              <input
                id="eventTitle"
                v-model="form.title"
                type="text"
                placeholder="Event title"
                required
              />
            </div>
            <div class="form-group">
              <label for="eventLocation">Location</label>
              <input
                id="eventLocation"
                v-model="form.location"
                type="text"
                placeholder="Event location"
                required
              />
            </div>
          </div>

          <div class="form-group">
            <label for="eventDescription">Description</label>
            <textarea
              id="eventDescription"
              v-model="form.description"
              rows="4"
              placeholder="Describe the event..."
              required
            ></textarea>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label for="eventDate">Date</label>
              <input
                id="eventDate"
                v-model="form.date"
                type="date"
                required
              />
            </div>
            <div class="form-group">
              <label for="eventTime">Time</label>
              <input
                id="eventTime"
                v-model="form.time"
                type="text"
                placeholder="e.g. 09:00 AM"
                required
              />
            </div>
          </div>

          <div class="form-group">
            <label for="eventAttendees">Expected Attendees</label>
            <input
              id="eventAttendees"
              v-model.number="form.expectedAttendees"
              type="number"
              min="0"
              placeholder="0"
              required
            />
          </div>

          <div v-if="formError" class="form-error">{{ formError }}</div>

          <div class="form-actions">
            <button type="button" class="cancel-btn" @click="closeFormModal">Cancel</button>
            <button type="submit" class="submit-btn" :disabled="submitting">
              {{ submitting ? 'Saving...' : (isEditing ? 'Update' : 'Create') }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div v-if="showDeleteModal" class="modal-overlay" @click.self="showDeleteModal = false">
      <div class="modal delete-modal">
        <h2>Delete Event</h2>
        <p>Are you sure you want to delete <strong>{{ eventToDelete?.title }}</strong>?</p>
        <p class="delete-warning">This action cannot be undone.</p>
        <div class="form-actions">
          <button class="cancel-btn" @click="showDeleteModal = false">Cancel</button>
          <button class="delete-confirm-btn" :disabled="submitting" @click="deleteEvent">
            {{ submitting ? 'Deleting...' : 'Delete' }}
          </button>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getAuthHeader, isLeader } from '../util/auth'

const API_BASE = '/api/events'
const canManage = computed(() => isLeader())

const events = ref([])
const loading = ref(false)
const error = ref('')
const activeFilter = ref('all')

const showDetailModal = ref(false)
const selectedEvent = ref(null)

const showFormModal = ref(false)
const isEditing = ref(false)
const submitting = ref(false)
const formError = ref('')
const form = ref({
  eventId: '',
  title: '',
  description: '',
  date: '',
  time: '',
  location: '',
  expectedAttendees: 0
})

// Delete modal
const showDeleteModal = ref(false)
const eventToDelete = ref(null)

function generateId() {
  return crypto.randomUUID()
}

function formatDay(dateStr) {
  if (!dateStr) return '--'
  const d = new Date(dateStr)
  return d.getDate()
}

function formatMonth(dateStr) {
  if (!dateStr) return ''
  const d = new Date(dateStr)
  return d.toLocaleString('default', { month: 'short' }).toUpperCase()
}

function truncate(text, max) {
  if (!text) return ''
  return text.length > max ? text.substring(0, max) + '…' : text
}


async function fetchEvents() {
  loading.value = true
  error.value = ''
  try {
    const endpoint = activeFilter.value === 'upcoming'
      ? `${API_BASE}/upcoming`
      : `${API_BASE}/getAll`
    const res = await fetch(endpoint)
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)
    events.value = await res.json()
  } catch (e) {
    error.value = `Failed to load events: ${e.message}`
  } finally {
    loading.value = false
  }
}

function setFilter(filter) {
  activeFilter.value = filter
  fetchEvents()
}


function openDetailModal(event) {
  selectedEvent.value = event
  showDetailModal.value = true
}

function resetForm() {
  form.value = {
    eventId: '',
    title: '',
    description: '',
    date: '',
    time: '',
    location: '',
    expectedAttendees: 0
  }
  formError.value = ''
}

function openCreateModal() {
  isEditing.value = false
  resetForm()
  form.value.eventId = generateId()
  showFormModal.value = true
}

function openEditModal(event) {
  isEditing.value = true
  formError.value = ''
  form.value = {
    eventId: event.eventId,
    title: event.title,
    description: event.description,
    date: event.date,
    time: event.time,
    location: event.location,
    expectedAttendees: event.expectedAttendees
  }
  showFormModal.value = true
}

function closeFormModal() {
  showFormModal.value = false
  resetForm()
}

async function submitForm() {
  submitting.value = true
  formError.value = ''

  const payload = {
    eventId: form.value.eventId,
    title: form.value.title,
    description: form.value.description,
    date: form.value.date,
    time: form.value.time,
    location: form.value.location,
    expectedAttendees: form.value.expectedAttendees
  }

  try {
    const url = isEditing.value ? `${API_BASE}/update` : `${API_BASE}/create`
    const method = isEditing.value ? 'PUT' : 'POST'

    const res = await fetch(url, {
      method,
      headers: { ...getAuthHeader(), 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })

    if (!res.ok) throw new Error(`Server responded with ${res.status}`)

    closeFormModal()
    await fetchEvents()
  } catch (e) {
    formError.value = `Failed to save event: ${e.message}`
  } finally {
    submitting.value = false
  }
}

function confirmDelete(event) {
  eventToDelete.value = event
  showDeleteModal.value = true
}

async function deleteEvent() {
  if (!eventToDelete.value) return
  submitting.value = true

  try {
    const res = await fetch(`${API_BASE}/delete/${eventToDelete.value.eventId}`, { method: 'DELETE', headers: { ...getAuthHeader() } })
    if (!res.ok) throw new Error(`Server responded with ${res.status}`)

    showDeleteModal.value = false
    eventToDelete.value = null
    await fetchEvents()
  } catch (e) {
    formError.value = `Failed to delete event: ${e.message}`
  } finally {
    submitting.value = false
  }
}

onMounted(fetchEvents)
</script>

<style src="../styles/events.css" scoped>
</style>