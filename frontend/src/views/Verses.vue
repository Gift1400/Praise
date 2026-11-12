<template>
  <div class="verses-page">

    <!-- Hero Section -->
    <section class="verses-hero">
      <div class="hero-overlay">
        <p class="eyebrow">A WORD FOR YOUR SEASON</p>
        <h1>What are you facing today?</h1>
        <p class="hero-subtitle">
          God’s Word has a promise for every moment. Explore scripture curated for your current season of life.
        </p>

        <!-- Search Input in Hero -->
        <div class="search-box">
          <input
            v-model="searchQuery"
            type="text"
            placeholder="Search keywords, topics, or book..."
          />
        </div>
      </div>
    </section>

    <!-- Main Content -->
    <div class="verses-container">

      <!-- Category Filter Pills & Leader Actions -->
      <div class="controls-bar">
        <div class="situation-pills">
          <button
            v-for="cat in situations"
            :key="cat.value"
            class="situation-btn"
            :class="{ active: selectedSituation === cat.value }"
            @click="selectedSituation = cat.value"
          >
            <span class="cat-icon">{{ cat.icon }}</span>
            <span>{{ cat.label }}</span>
          </button>
        </div>

        <button v-if="canManage" class="add-verse-btn" @click="openAddModal">
          <span>+ Add Verse</span>
        </button>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="state-box">
        <p>Searching scriptures...</p>
      </div>

      <!-- Empty State -->
      <div v-else-if="filteredVerses.length === 0" class="state-box empty">
        <p>No scriptures found matching your criteria. Try another category or search term.</p>
      </div>

      <!-- Verses Grid -->
      <div v-else class="verses-grid">
        <article
          v-for="v in filteredVerses"
          :key="v.verseId || v.id"
          class="verse-card"
        >
          <div class="card-header">
            <span class="situation-badge" :class="v.lifeSituation?.toLowerCase()">
              {{ v.lifeSituation }}
            </span>
            <button
              v-if="canManage"
              class="delete-btn"
              title="Delete verse"
              @click="deleteVerse(v)"
            >
              ✕
            </button>
          </div>

          <blockquote class="verse-quote">
            <span class="quote-mark">“</span>
            <p>{{ v.text }}</p>
            <span class="quote-mark end">”</span>
          </blockquote>

          <footer class="verse-reference">
            <strong>{{ v.book }} {{ v.chapter }}:{{ v.verseNumber }}</strong>
          </footer>
        </article>
      </div>

    </div>

    <!-- Add Verse Modal (Leaders Only) -->
    <div v-if="showModal" class="modal-overlay" @click.self="showModal = false">
      <div class="modal modal-card">
        <button class="modal-close" @click="showModal = false">✕</button>
        <h2>Add Bible Verse</h2>
        <p class="modal-subtitle">Add a scripture promise to encourage church members.</p>

        <form @submit.prevent="createVerse" class="verse-form">
          <div class="form-row">
            <div class="form-group">
              <label for="book">Book</label>
              <input id="book" v-model="form.book" placeholder="e.g. Philippians" required />
            </div>
            <div class="form-group">
              <label for="chapter">Chapter</label>
              <input id="chapter" v-model.number="form.chapter" type="number" min="1" required />
            </div>
            <div class="form-group">
              <label for="verseNumber">Verse</label>
              <input id="verseNumber" v-model.number="form.verseNumber" type="number" min="1" required />
            </div>
          </div>

          <div class="form-group">
            <label for="situation">Life Situation</label>
            <select id="situation" v-model="form.lifeSituation" required>
              <option value="ANXIETY">Anxiety &amp; Worry</option>
              <option value="GRIEF">Grief &amp; Loss</option>
              <option value="CONFUSION">Confusion &amp; Guidance</option>
              <option value="HAPPY">Praise &amp; Joy (Happy)</option>
              <option value="SAD">Sadness &amp; Comfort</option>
            </select>
          </div>

          <div class="form-group">
            <label for="text">Scripture Text</label>
            <textarea
              id="text"
              v-model="form.text"
              rows="4"
              placeholder="Enter verse text..."
              required
            ></textarea>
          </div>

          <div v-if="formError" class="error-msg">{{ formError }}</div>

          <div class="modal-actions">
            <button type="button" class="btn-cancel" @click="showModal = false">Cancel</button>
            <button type="submit" class="btn-save" :disabled="submitting">
              {{ submitting ? 'Saving...' : 'Save Verse' }}
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

const canManage = computed(() => isLeader())

const searchQuery = ref('')
const selectedSituation = ref('ALL')
const loading = ref(false)
const showModal = ref(false)
const submitting = ref(false)
const formError = ref('')

const situations = [
  { value: 'ALL', label: 'All Seasons', icon: '✨' },
  { value: 'ANXIETY', label: 'Anxiety & Peace', icon: '🌿' },
  { value: 'GRIEF', label: 'Grief & Loss', icon: '🕊️' },
  { value: 'CONFUSION', label: 'Guidance & Clarity', icon: '🧭' },
  { value: 'HAPPY', label: 'Joy & Thanksgiving', icon: '☀️' },
  { value: 'SAD', label: 'Comfort & Hope', icon: '❤️' }
]

const verses = ref([])

const defaultVerses = [
  {
    verseId: 'bv-1',
    book: 'Philippians',
    chapter: 4,
    verseNumber: 6,
    lifeSituation: 'ANXIETY',
    text: 'Do not be anxious about anything, but in every situation, by prayer and petition, with thanksgiving, present your requests to God. And the peace of God, which transcends all understanding, will guard your hearts and your minds in Christ Jesus.'
  },
  {
    verseId: 'bv-2',
    book: '1 Peter',
    chapter: 5,
    verseNumber: 7,
    lifeSituation: 'ANXIETY',
    text: 'Cast all your anxiety on Him because He cares for you.'
  },
  {
    verseId: 'bv-3',
    book: 'Psalm',
    chapter: 34,
    verseNumber: 18,
    lifeSituation: 'GRIEF',
    text: 'The Lord is close to the brokenhearted and saves those who are crushed in spirit.'
  },
  {
    verseId: 'bv-4',
    book: 'Matthew',
    chapter: 5,
    verseNumber: 4,
    lifeSituation: 'GRIEF',
    text: 'Blessed are those who mourn, for they will be comforted.'
  },
  {
    verseId: 'bv-5',
    book: 'Proverbs',
    chapter: 3,
    verseNumber: 5,
    lifeSituation: 'CONFUSION',
    text: 'Trust in the Lord with all your heart and lean not on your own understanding; in all your ways submit to Him, and He will make your paths straight.'
  },
  {
    verseId: 'bv-6',
    book: 'James',
    chapter: 1,
    verseNumber: 5,
    lifeSituation: 'CONFUSION',
    text: 'If any of you lacks wisdom, you should ask God, who gives generously to all without finding fault, and it will be given to you.'
  },
  {
    verseId: 'bv-7',
    book: 'Psalm',
    chapter: 118,
    verseNumber: 24,
    lifeSituation: 'HAPPY',
    text: 'This is the day the Lord has made; we will rejoice and be glad in it.'
  },
  {
    verseId: 'bv-8',
    book: 'Nehemiah',
    chapter: 8,
    verseNumber: 10,
    lifeSituation: 'HAPPY',
    text: 'Do not grieve, for the joy of the Lord is your strength.'
  },
  {
    verseId: 'bv-9',
    book: 'Psalm',
    chapter: 42,
    verseNumber: 11,
    lifeSituation: 'SAD',
    text: 'Why, my soul, are you downcast? Why so disturbed within me? Put your hope in God, for I will yet praise Him, my Savior and my God.'
  }
]

const form = ref({
  book: '',
  chapter: 1,
  verseNumber: 1,
  lifeSituation: 'ANXIETY',
  text: ''
})

async function fetchVerses() {
  loading.value = true
  try {
    const res = await fetch('/api/bibleVerse/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data) && data.length > 0) {
        verses.value = data
      } else {
        verses.value = defaultVerses
      }
    } else {
      verses.value = defaultVerses
    }
  } catch {
    verses.value = defaultVerses
  } finally {
    loading.value = false
  }
}

const filteredVerses = computed(() => {
  return verses.value.filter(v => {
    const matchSituation =
      selectedSituation.value === 'ALL' ||
      String(v.lifeSituation).toUpperCase() === selectedSituation.value

    const q = searchQuery.value.trim().toLowerCase()
    const matchQuery =
      !q ||
      v.text?.toLowerCase().includes(q) ||
      v.book?.toLowerCase().includes(q) ||
      v.lifeSituation?.toLowerCase().includes(q)

    return matchSituation && matchQuery
  })
})

function openAddModal() {
  form.value = {
    book: '',
    chapter: 1,
    verseNumber: 1,
    lifeSituation: 'ANXIETY',
    text: ''
  }
  formError.value = ''
  showModal.value = true
}

async function createVerse() {
  submitting.value = true
  formError.value = ''

  const payload = {
    verseId: crypto.randomUUID(),
    book: form.value.book.trim(),
    chapter: form.value.chapter,
    verseNumber: form.value.verseNumber,
    lifeSituation: form.value.lifeSituation,
    text: form.value.text.trim()
  }

  try {
    const res = await fetch('/api/bibleVerse/create', {
      method: 'POST',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
    if (!res.ok) throw new Error('Failed to save verse')
    showModal.value = false
    await fetchVerses()
  } catch {
    verses.value.unshift(payload)
    showModal.value = false
  } finally {
    submitting.value = false
  }
}

async function deleteVerse(v) {
  const id = v.verseId || v.id
  if (!confirm(`Delete verse from ${v.book}?`)) return
  try {
    await fetch(`/api/bibleVerse/delete/${id}`, {
      method: 'DELETE',
      headers: { ...getAuthHeader() }
    })
  } catch {}
  verses.value = verses.value.filter(item => (item.verseId || item.id) !== id)
}

onMounted(fetchVerses)
</script>

<style scoped>
.verses-page {
  min-height: 100vh;
  background: #fdfcf9;
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
}

/* Hero */
.verses-hero {
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
  max-width: 680px;
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

.verses-hero h1 {
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
  margin: 0 0 26px;
}

.search-box input {
  width: 100%;
  max-width: 480px;
  height: 48px;
  border-radius: 24px;
  border: 1px solid rgba(255, 255, 255, 0.3);
  background: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(8px);
  padding: 0 22px;
  font-size: 14px;
  color: #ffffff;
  outline: none;
  font-family: inherit;
  transition: all 0.2s;
}

.search-box input::placeholder {
  color: rgba(255, 255, 255, 0.7);
}

.search-box input:focus {
  background: rgba(255, 255, 255, 0.25);
  border-color: #dfba73;
}

/* Controls */
.verses-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 24px 80px;
}

.controls-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  flex-wrap: wrap;
  margin-bottom: 36px;
}

.situation-pills {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.situation-btn {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  background: #ffffff;
  border: 1px solid #e2ddd3;
  color: #5c5243;
  padding: 8px 16px;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.situation-btn:hover {
  background: #fbf9f5;
  border-color: #cbbba4;
}

.situation-btn.active {
  background: #80633e;
  color: #ffffff;
  border-color: #80633e;
  box-shadow: 0 2px 8px rgba(128, 99, 62, 0.25);
}

.add-verse-btn {
  background: #9b6d00;
  color: #ffffff;
  border: none;
  padding: 10px 18px;
  border-radius: 8px;
  font-size: 13.5px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.add-verse-btn:hover {
  background: #805a00;
}

.state-box {
  background: #ffffff;
  border: 1px dashed #dcd4c7;
  border-radius: 16px;
  padding: 40px;
  text-align: center;
  color: #857a68;
}

/* Verses Grid */
.verses-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(360px, 1fr));
  gap: 24px;
}

.verse-card {
  background: #ffffff;
  border: 1px solid #eae5db;
  border-radius: 16px;
  padding: 28px;
  box-shadow: 0 4px 18px rgba(0, 0, 0, 0.03);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s, box-shadow 0.2s;
}

.verse-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 28px rgba(0, 0, 0, 0.07);
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 16px;
}

.situation-badge {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.8px;
  text-transform: uppercase;
  padding: 3px 9px;
  border-radius: 6px;
  background: #fbf5ea;
  color: #80633e;
}

.delete-btn {
  background: transparent;
  border: none;
  color: #b91c1c;
  font-size: 13px;
  cursor: pointer;
  padding: 2px 6px;
  border-radius: 4px;
}

.delete-btn:hover {
  background: #fee2e2;
}

.verse-quote {
  margin: 0 0 20px;
  position: relative;
}

.quote-mark {
  font-family: Georgia, serif;
  font-size: 32px;
  color: #c9bda9;
  line-height: 0;
  vertical-align: -10px;
}

.quote-mark.end {
  vertical-align: -4px;
}

.verse-quote p {
  display: inline;
  font-family: Georgia, serif;
  font-size: 16.5px;
  line-height: 1.7;
  color: #2b241c;
}

.verse-reference {
  font-size: 14px;
  color: #9b6d00;
  font-weight: 600;
  padding-top: 14px;
  border-top: 1px solid #f2ede5;
}

/* Modal */
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

.modal-card {
  background: #ffffff;
  border-radius: 18px;
  width: 100%;
  max-width: 540px;
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

.modal-card h2 {
  font-family: Georgia, serif;
  font-size: 24px;
  margin: 0 0 4px;
}

.modal-subtitle {
  font-size: 13px;
  color: #7a7062;
  margin: 0 0 20px;
}

.verse-form .form-row {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr;
  gap: 12px;
}

.verse-form .form-group {
  margin-bottom: 16px;
}

.verse-form label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 6px;
}

.verse-form input,
.verse-form select,
.verse-form textarea {
  width: 100%;
  padding: 10px 14px;
  border: 1px solid #ddd7cc;
  border-radius: 8px;
  font-size: 14px;
  font-family: inherit;
  box-sizing: border-box;
}

.verse-form input:focus,
.verse-form select:focus,
.verse-form textarea:focus {
  outline: none;
  border-color: #9b6d00;
}

.error-msg {
  color: #b91c1c;
  background: #fdf2f2;
  padding: 8px 12px;
  border-radius: 6px;
  font-size: 13px;
  margin-bottom: 14px;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 20px;
}

.btn-cancel {
  background: #f3efe9;
  border: none;
  padding: 10px 18px;
  border-radius: 8px;
  font-size: 14px;
  color: #555;
  cursor: pointer;
}

.btn-save {
  background: #9b6d00;
  color: #fff;
  border: none;
  padding: 10px 22px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

@media (max-width: 600px) {
  .verses-hero h1 {
    font-size: 34px;
  }
  .verse-form .form-row {
    grid-template-columns: 1fr;
  }
}
</style>
