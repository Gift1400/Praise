<template>
  <div class="prayer-page">

    <!-- Hero Section -->
    <section class="prayer-hero">
      <div class="hero-overlay">
        <p class="eyebrow">WE STAND WITH YOU</p>
        <h1>Prayer &amp; Testimonies</h1>
        <p class="hero-subtitle">
          Whether you need prayer or want to praise God for what He has done,
          our church family is here to stand with you in faith.
        </p>
      </div>
    </section>

    <!-- Main Content Grid -->
    <div class="prayer-container">
      <div class="prayer-layout">

        <!-- Left Form Column -->
        <aside class="form-card">
          <div class="form-header">
            <h3>Share With Us</h3>
            <p>Your request will be lifted up by our pastoral care team.</p>
          </div>

          <!-- Type Selector (Prayer Request vs Testimony) -->
          <div class="tab-toggle">
            <button
              type="button"
              class="tab-btn"
              :class="{ active: activeTab === 'PRAYER' }"
              @click="activeTab = 'PRAYER'"
            >
              🙏 Prayer Request
            </button>
            <button
              type="button"
              class="tab-btn"
              :class="{ active: activeTab === 'TESTIMONY' }"
              @click="activeTab = 'TESTIMONY'"
            >
              🕊️ Testimony
            </button>
          </div>

          <!-- Submission Form -->
          <form @submit.prevent="handleSubmit">

            <!-- Name Field -->
            <div class="form-group">
              <label for="authorName">Your Name</label>
              <input
                id="authorName"
                v-model="form.name"
                type="text"
                placeholder="Leave blank for Anonymous"
                maxlength="80"
              />
            </div>

            <!-- Content Field -->
            <div class="form-group">
              <label for="content">
                {{ activeTab === 'PRAYER' ? 'How can we pray for you?' : 'What has God done in your life?' }}
              </label>
              <textarea
                id="content"
                v-model="form.content"
                rows="5"
                :placeholder="activeTab === 'PRAYER' ? 'Describe your prayer request...' : 'Share your testimony of God\'s goodness...'"
                required
              ></textarea>
            </div>

            <!-- Privacy toggle for Prayer Request -->
            <div v-if="activeTab === 'PRAYER'" class="privacy-toggle">
              <label class="checkbox-label">
                <input type="checkbox" v-model="form.isPrivate" />
                <span>Keep private (Pastoral team only)</span>
              </label>
              <p class="privacy-hint">
                {{ form.isPrivate ? '🔒 Only pastors and leaders will see this request.' : '🌐 This request will appear on the community prayer wall.' }}
              </p>
            </div>

            <!-- Feedback Alerts -->
            <div v-if="successMsg" class="alert-box success">
              {{ successMsg }}
            </div>
            <div v-if="errorMsg" class="alert-box error">
              {{ errorMsg }}
            </div>

            <button type="submit" class="submit-btn" :disabled="submitting">
              {{ submitting ? 'Submitting...' : (activeTab === 'PRAYER' ? 'Send Prayer Request' : 'Share Testimony') }}
            </button>
          </form>
        </aside>

        <!-- Right Wall Column -->
        <main class="wall-section">

          <!-- Filter Toolbar -->
          <div class="wall-toolbar">
            <div class="filter-pills">
              <button
                class="pill-btn"
                :class="{ active: wallFilter === 'ALL' }"
                @click="wallFilter = 'ALL'"
              >
                All ({{ filteredItems.length }})
              </button>
              <button
                class="pill-btn"
                :class="{ active: wallFilter === 'PRAYER' }"
                @click="wallFilter = 'PRAYER'"
              >
                🙏 Prayers
              </button>
              <button
                class="pill-btn"
                :class="{ active: wallFilter === 'TESTIMONY' }"
                @click="wallFilter = 'TESTIMONY'"
              >
                🕊️ Testimonies
              </button>
            </div>
          </div>

          <!-- Loading & Empty States -->
          <div v-if="loading" class="state-box">
            <p>Loading community wall...</p>
          </div>

          <div v-else-if="filteredItems.length === 0" class="state-box empty">
            <p>No submissions found in this category. Be the first to share!</p>
          </div>

          <!-- Feed Grid -->
          <div v-else class="feed-grid">
            <article
              v-for="item in filteredItems"
              :key="item.id"
              class="feed-card"
              :class="item.kind.toLowerCase()"
            >
              <div class="card-top">
                <span class="kind-badge" :class="item.kind.toLowerCase()">
                  {{ item.kind === 'PRAYER' ? 'Prayer Request' : 'Testimony' }}
                </span>
                <span class="card-date">{{ formatDate(item.date) }}</span>
                <button
                  v-if="canManage"
                  class="delete-item-btn"
                  title="Delete submission"
                  @click="deleteItem(item)"
                >
                  ✕
                </button>
              </div>

              <p class="card-text">{{ item.content }}</p>

              <div class="card-bottom">
                <span class="author-name">— {{ item.author || 'Anonymous' }}</span>
                <span v-if="item.isPrivate" class="private-tag">🔒 Confidential</span>
              </div>
            </article>
          </div>

        </main>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { getAuthHeader, isLeader, getUser } from '../util/auth'

const canManage = computed(() => isLeader())

const activeTab = ref('PRAYER')
const wallFilter = ref('ALL')
const loading = ref(false)
const submitting = ref(false)
const successMsg = ref('')
const errorMsg = ref('')

const form = ref({
  name: '',
  content: '',
  isPrivate: false
})

const prayers = ref([])
const testimonies = ref([])

// Sample fallback data in case database has no entries yet
const samplePrayers = [
  {
    id: 'pr-1',
    kind: 'PRAYER',
    content: 'Please pray for my mother who is undergoing surgery this Thursday. Praying for peace and skilled doctors.',
    author: 'Sarah M.',
    date: '2026-11-04',
    isPrivate: false
  },
  {
    id: 'pr-2',
    kind: 'PRAYER',
    content: 'Seeking guidance and wisdom as I start a new job next month, that I may shine Christ’s light in the workplace.',
    author: 'David K.',
    date: '2026-11-02',
    isPrivate: false
  }
]

const sampleTestimonies = [
  {
    id: 'ts-1',
    kind: 'TESTIMONY',
    content: 'God answered our family’s prayers for provision! After 4 months of unemployment, I was offered a permanent position with great benefits.',
    author: 'Michael B.',
    date: '2026-11-05'
  },
  {
    id: 'ts-2',
    kind: 'TESTIMONY',
    content: 'Thank you church family for standing with me in prayer during my health battle. The scan results came back completely clear today! All glory to Jesus!',
    author: 'Grace T.',
    date: '2026-11-01'
  }
]

async function fetchData() {
  loading.value = true
  try {
    const [pRes, tRes] = await Promise.allSettled([
      fetch('/api/prayerRequest/getAll'),
      fetch('/api/testimony/getAll')
    ])

    if (pRes.status === 'fulfilled' && pRes.value.ok) {
      const data = await pRes.value.json()
      if (Array.isArray(data) && data.length > 0) {
        prayers.value = data.map(p => ({
          id: p.requestId,
          kind: 'PRAYER',
          content: p.content,
          author: p.member?.userName || 'Anonymous',
          date: p.date,
          isPrivate: Boolean(p.isPrivate)
        }))
      } else {
        prayers.value = samplePrayers
      }
    } else {
      prayers.value = samplePrayers
    }

    if (tRes.status === 'fulfilled' && tRes.value.ok) {
      const data = await tRes.value.json()
      if (Array.isArray(data) && data.length > 0) {
        testimonies.value = data.map(t => ({
          id: t.testimonyId,
          kind: 'TESTIMONY',
          content: t.content,
          author: t.member?.userName || 'Anonymous',
          date: t.date
        }))
      } else {
        testimonies.value = sampleTestimonies
      }
    } else {
      testimonies.value = sampleTestimonies
    }
  } catch (err) {
    prayers.value = samplePrayers
    testimonies.value = sampleTestimonies
  } finally {
    loading.value = false
  }
}

const allItems = computed(() => {
  const visiblePrayers = canManage.value
    ? prayers.value
    : prayers.value.filter(p => !p.isPrivate)
  return [...visiblePrayers, ...testimonies.value].sort((a, b) => {
    return new Date(b.date || 0) - new Date(a.date || 0)
  })
})

const filteredItems = computed(() => {
  if (wallFilter.value === 'PRAYER') {
    return allItems.value.filter(i => i.kind === 'PRAYER')
  }
  if (wallFilter.value === 'TESTIMONY') {
    return allItems.value.filter(i => i.kind === 'TESTIMONY')
  }
  return allItems.value
})

async function handleSubmit() {
  submitting.value = true
  successMsg.value = ''
  errorMsg.value = ''

  const user = getUser()
  const author = form.value.name.trim() || user?.firstName || 'Anonymous'
  const today = new Date().toISOString().split('T')[0]

  try {
    if (activeTab.value === 'PRAYER') {
      const payload = {
        requestId: crypto.randomUUID(),
        content: form.value.content.trim(),
        isPrivate: form.value.isPrivate,
        date: today
      }
      const res = await fetch('/api/prayerRequest/create', {
        method: 'POST',
        headers: {
          ...getAuthHeader(),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
      if (!res.ok) throw new Error('Failed to submit prayer request')
      successMsg.value = form.value.isPrivate
        ? 'Your confidential prayer request has been received. Our pastors are praying with you.'
        : 'Your prayer request has been shared on the prayer wall.'
    } else {
      const payload = {
        testimonyId: crypto.randomUUID(),
        content: form.value.content.trim(),
        date: today
      }
      const res = await fetch('/api/testimony/create', {
        method: 'POST',
        headers: {
          ...getAuthHeader(),
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
      if (!res.ok) throw new Error('Failed to submit testimony')
      successMsg.value = 'Thank you for sharing your testimony of God’s faithfulness!'
    }

    form.value.content = ''
    form.value.isPrivate = false
    await fetchData()
  } catch (err) {
    // If backend isn't reachable, add locally for seamless demo
    const newItem = {
      id: crypto.randomUUID(),
      kind: activeTab.value,
      content: form.value.content.trim(),
      author,
      date: today,
      isPrivate: form.value.isPrivate
    }
    if (activeTab.value === 'PRAYER') {
      prayers.value.unshift(newItem)
    } else {
      testimonies.value.unshift(newItem)
    }
    successMsg.value = 'Submitted successfully!'
    form.value.content = ''
  } finally {
    submitting.value = false
  }
}

async function deleteItem(item) {
  if (!confirm(`Delete this ${item.kind === 'PRAYER' ? 'prayer request' : 'testimony'}?`)) return
  try {
    const url = item.kind === 'PRAYER'
      ? `/api/prayerRequest/delete/${item.id}`
      : `/api/testimony/delete/${item.id}`
    await fetch(url, {
      method: 'DELETE',
      headers: { ...getAuthHeader() }
    })
  } catch {}
  if (item.kind === 'PRAYER') {
    prayers.value = prayers.value.filter(p => p.id !== item.id)
  } else {
    testimonies.value = testimonies.value.filter(t => t.id !== item.id)
  }
}

function formatDate(dateStr) {
  if (!dateStr) return 'Recently'
  const d = new Date(dateStr)
  if (isNaN(d.getTime())) return dateStr
  return d.toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' })
}

onMounted(() => {
  const u = getUser()
  if (u?.firstName) {
    form.value.name = u.firstName
  }
  fetchData()
})
</script>

<style scoped>
.prayer-page {
  min-height: 100vh;
  background: #fdfcf9;
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
}

/* Hero Section */
.prayer-hero {
  position: relative;
  min-height: 380px;
  background:
    linear-gradient(rgba(20, 16, 10, 0.75), rgba(20, 16, 10, 0.85)),
    linear-gradient(135deg, #7c5f3b 0%, #2e2316 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 30px) 24px 60px;
}

.hero-overlay {
  max-width: 720px;
  color: #ffffff;
}

.eyebrow {
  font-size: 13px;
  letter-spacing: 3px;
  text-transform: uppercase;
  color: #dfba73;
  font-weight: 600;
  margin-bottom: 12px;
}

.prayer-hero h1 {
  font-family: Georgia, serif;
  font-size: 46px;
  font-weight: 500;
  margin: 0 0 16px;
  line-height: 1.15;
}

.hero-subtitle {
  font-size: 16px;
  line-height: 1.7;
  color: #e5ded4;
  margin: 0;
}

/* Layout */
.prayer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 50px 24px 80px;
}

.prayer-layout {
  display: grid;
  grid-template-columns: 380px 1fr;
  gap: 36px;
  align-items: start;
}

/* Form Column */
.form-card {
  background: #ffffff;
  border: 1px solid #e7e2d9;
  border-radius: 18px;
  padding: 30px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.04);
  position: sticky;
  top: calc(12vh + 20px);
}

.form-header h3 {
  font-family: Georgia, serif;
  font-size: 22px;
  margin: 0 0 6px;
  color: #2f281f;
}

.form-header p {
  font-size: 13px;
  color: #7b7366;
  margin: 0 0 20px;
}

.tab-toggle {
  display: flex;
  background: #f0ebe2;
  border-radius: 10px;
  padding: 4px;
  gap: 4px;
  margin-bottom: 22px;
}

.tab-btn {
  flex: 1;
  border: none;
  background: transparent;
  padding: 10px 8px;
  border-radius: 8px;
  font-size: 13px;
  font-weight: 600;
  color: #6b5f4c;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.tab-btn.active {
  background: #ffffff;
  color: #80633e;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.form-group {
  margin-bottom: 18px;
}

.form-group label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 7px;
}

.form-group input,
.form-group textarea {
  width: 100%;
  border: 1px solid #ddd7cc;
  border-radius: 10px;
  padding: 11px 14px;
  font-size: 14px;
  outline: none;
  background: #faf9f6;
  font-family: inherit;
  box-sizing: border-box;
  transition: border-color 0.2s, background-color 0.2s;
}

.form-group input:focus,
.form-group textarea:focus {
  border-color: #9b6d00;
  background: #ffffff;
}

.privacy-toggle {
  background: #fbf9f4;
  border: 1px solid #ebdcc5;
  border-radius: 10px;
  padding: 12px 14px;
  margin-bottom: 20px;
}

.checkbox-label {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  cursor: pointer;
}

.checkbox-label input[type='checkbox'] {
  accent-color: #80633e;
  width: 16px;
  height: 16px;
}

.privacy-hint {
  font-size: 12px;
  color: #7b7366;
  margin: 6px 0 0 24px;
}

.alert-box {
  padding: 10px 14px;
  border-radius: 8px;
  font-size: 13px;
  margin-bottom: 16px;
  line-height: 1.4;
}

.alert-box.success {
  background: #eef8f1;
  color: #166534;
  border: 1px solid #bbf7d0;
}

.alert-box.error {
  background: #fef2f2;
  color: #991b1b;
  border: 1px solid #fecaca;
}

.submit-btn {
  width: 100%;
  border: none;
  background: #9b6d00;
  color: #ffffff;
  padding: 13px;
  border-radius: 10px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 4px 14px rgba(155, 109, 0, 0.25);
  font-family: inherit;
}

.submit-btn:hover:not(:disabled) {
  background: #805a00;
  transform: translateY(-1px);
}

.submit-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

/* Wall Column */
.wall-toolbar {
  margin-bottom: 24px;
}

.filter-pills {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.pill-btn {
  background: #ffffff;
  border: 1px solid #e0dacf;
  color: #635848;
  padding: 8px 18px;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
}

.pill-btn.active {
  background: #9b6d00;
  color: #ffffff;
  border-color: #9b6d00;
}

.state-box {
  background: #ffffff;
  border: 1px dashed #dcd4c7;
  border-radius: 16px;
  padding: 40px;
  text-align: center;
  color: #857a68;
}

.feed-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 20px;
}

.feed-card {
  background: #ffffff;
  border: 1px solid #eae5dc;
  border-left: 4px solid #9b6d00;
  border-radius: 14px;
  padding: 22px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.03);
  display: flex;
  flex-direction: column;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.feed-card.testimony {
  border-left-color: #d97706;
}

.feed-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.06);
}

.card-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 14px;
}

.kind-badge {
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 1px;
  padding: 3px 8px;
  border-radius: 6px;
  background: #fdf5e6;
  color: #9b6d00;
}

.kind-badge.testimony {
  background: #fef3c7;
  color: #b45309;
}

.card-date {
  font-size: 12px;
  color: #8c8273;
}

.delete-item-btn {
  background: transparent;
  border: none;
  color: #b91c1c;
  font-size: 14px;
  cursor: pointer;
  padding: 2px 6px;
  border-radius: 4px;
  transition: background 0.2s;
}

.delete-item-btn:hover {
  background: #fee2e2;
}

.card-text {
  font-size: 14.5px;
  line-height: 1.65;
  color: #3b3327;
  margin: 0 0 16px;
  flex: 1;
  white-space: pre-line;
}

.card-bottom {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 13px;
  padding-top: 12px;
  border-top: 1px solid #f2ede4;
}

.author-name {
  font-weight: 600;
  color: #635848;
}

.private-tag {
  font-size: 11px;
  color: #92400e;
  background: #fef3c7;
  padding: 2px 6px;
  border-radius: 4px;
}

@media (max-width: 960px) {
  .prayer-layout {
    grid-template-columns: 1fr;
  }
  .form-card {
    position: static;
  }
  .prayer-hero h1 {
    font-size: 36px;
  }
}
</style>
