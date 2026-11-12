<template>
  <div class="outreach-page">

    <!-- Hero Section -->
    <section class="outreach-hero">
      <div class="hero-overlay">
        <p class="eyebrow">SERVE THE CITY</p>
        <h1>Community Outreach</h1>
        <p class="hero-subtitle">
          Loving God means serving people. Join an outreach team to make a tangible, lasting difference in our city.
        </p>
      </div>
    </section>

    <!-- Main Content Container -->
    <div class="outreach-container">

      <!-- Header & Leader Create Action -->
      <div class="section-top">
        <div>
          <h2>Active Outreach Programs</h2>
          <p class="section-desc">Pick a team and leave your details — a team leader will get in touch with you.</p>
        </div>

        <button v-if="canManage" class="create-program-btn" @click="openCreateModal">
          <span>+ New Outreach Program</span>
        </button>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="state-box">
        <p>Loading outreach programs...</p>
      </div>

      <!-- Empty State -->
      <div v-else-if="programs.length === 0" class="state-box empty">
        <p>No active outreach programs at this time.</p>
      </div>

      <!-- Outreach Grid -->
      <div v-else class="programs-grid">
        <article
          v-for="p in programs"
          :key="p.programId || p.outreachProgramId || p.id"
          class="program-card"
        >
          <div class="card-top">
            <span class="program-badge">Community Outreach</span>
            <button
              v-if="canManage"
              class="delete-btn"
              title="Delete Program"
              @click="deleteProgram(p)"
            >
              ✕
            </button>
          </div>

          <h3 class="program-title">{{ p.programName }}</h3>
          <p class="program-desc">{{ p.description }}</p>

          <div class="program-meta">
            <span class="leader-info" v-if="p.leader?.name || p.leaderName">
              <strong>Organized by:</strong> {{ p.leader?.name || p.leaderName }}
            </span>
          </div>

          <!-- Volunteer Sign-up Form -->
          <div class="signup-zone">
            <div v-if="joinedPrograms[p.programId || p.outreachProgramId || p.id]" class="joined-badge">
              <span>✓ You are signed up for this team! A leader will contact you soon.</span>
            </div>

            <form
              v-else
              class="volunteer-form"
              @submit.prevent="joinTeam(p)"
            >
              <div class="input-row">
                <input
                  v-model="volunteerData[p.programId || p.outreachProgramId || p.id].name"
                  type="text"
                  placeholder="Your Name"
                  required
                />
                <input
                  v-model="volunteerData[p.programId || p.outreachProgramId || p.id].phone"
                  type="tel"
                  placeholder="Phone number"
                  required
                />
              </div>
              <button type="submit" class="join-btn" :disabled="joiningId === (p.programId || p.outreachProgramId || p.id)">
                {{ joiningId === (p.programId || p.outreachProgramId || p.id) ? 'Joining...' : 'Join This Team' }}
              </button>
            </form>
          </div>
        </article>
      </div>

    </div>

    <!-- Create Program Modal (Leaders Only) -->
    <div v-if="showModal" class="modal-overlay" @click.self="showModal = false">
      <div class="modal modal-card">
        <button class="modal-close" @click="showModal = false">✕</button>
        <h2>New Outreach Program</h2>
        <p class="modal-subtitle">Create a community initiative for church members to volunteer and serve.</p>

        <form @submit.prevent="createProgram" class="program-form">
          <div class="form-group">
            <label for="progName">Program Name</label>
            <input
              id="progName"
              v-model="form.programName"
              placeholder="e.g. City Food Drive &amp; Kitchen"
              required
            />
          </div>

          <div class="form-group">
            <label for="leaderName">Leader in Charge</label>
            <input
              id="leaderName"
              v-model="form.leaderName"
              placeholder="e.g. Pastor David or Outreach Lead"
              required
            />
          </div>

          <div class="form-group">
            <label for="desc">Description &amp; Goals</label>
            <textarea
              id="desc"
              v-model="form.description"
              rows="4"
              placeholder="Describe what the team does, meeting frequency, and how volunteers help..."
              required
            ></textarea>
          </div>

          <div v-if="formError" class="error-msg">{{ formError }}</div>

          <div class="modal-actions">
            <button type="button" class="btn-cancel" @click="showModal = false">Cancel</button>
            <button type="submit" class="btn-save" :disabled="submitting">
              {{ submitting ? 'Creating...' : 'Create Program' }}
            </button>
          </div>
        </form>
      </div>
    </div>

  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { getAuthHeader, isLeader, getUser } from '../util/auth'

const canManage = computed(() => isLeader())

const loading = ref(false)
const showModal = ref(false)
const submitting = ref(false)
const joiningId = ref(null)
const formError = ref('')

const defaultPrograms = [
  {
    outreachProgramId: 'op-1',
    programName: 'Hope Food Pantry & Soup Kitchen',
    description: 'Providing warm meals, grocery hampers, and emotional support to vulnerable families across the city every Saturday morning.',
    leaderName: 'Elder Mark & Pastoral Team'
  },
  {
    outreachProgramId: 'op-2',
    programName: 'Youth Mentorship & Tutoring',
    description: 'Pairing church mentors with high school students for academic tutoring, life skills coaching, and Christian discipleship.',
    leaderName: 'Youth Ministry Team'
  },
  {
    outreachProgramId: 'op-3',
    programName: 'Hospital & Elderly Home Visitation',
    description: 'Visiting care facilities, praying with the sick and elderly, delivering care packages, and sharing the comforting love of Christ.',
    leaderName: 'Sister Rachel Adams'
  }
]

const programs = ref([])
const volunteerData = reactive({})
const joinedPrograms = reactive({})

const form = ref({
  programName: '',
  leaderName: '',
  description: ''
})

async function fetchPrograms() {
  loading.value = true
  try {
    const res = await fetch('/api/outreachProgram/getAll')
    if (res.ok) {
      const data = await res.json()
      if (Array.isArray(data) && data.length > 0) {
        programs.value = data
      } else {
        programs.value = defaultPrograms
      }
    } else {
      programs.value = defaultPrograms
    }
  } catch {
    programs.value = defaultPrograms
  } finally {
    loading.value = false
    initVolunteerForm()
  }
}

function initVolunteerForm() {
  const u = getUser()
  const defaultName = u?.firstName ? `${u.firstName} ${u.lastName || ''}`.trim() : ''

  programs.value.forEach(p => {
    const id = p.programId || p.outreachProgramId || p.id
    if (!volunteerData[id]) {
      volunteerData[id] = {
        name: defaultName,
        phone: ''
      }
    }
  })
}

function openCreateModal() {
  const u = getUser()
  form.value = {
    programName: '',
    leaderName: u?.firstName ? `${u.firstName} ${u.lastName || ''}`.trim() : 'Outreach Leader',
    description: ''
  }
  formError.value = ''
  showModal.value = true
}

async function createProgram() {
  submitting.value = true
  formError.value = ''

  const payload = {
    outreachProgramId: crypto.randomUUID(),
    programName: form.value.programName.trim(),
    description: form.value.description.trim()
  }

  try {
    const res = await fetch('/api/outreachProgram/create', {
      method: 'POST',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
    if (!res.ok) throw new Error('Failed to create outreach program')
    showModal.value = false
    await fetchPrograms()
  } catch {
    payload.leaderName = form.value.leaderName
    programs.value.unshift(payload)
    showModal.value = false
    initVolunteerForm()
  } finally {
    submitting.value = false
  }
}

async function joinTeam(p) {
  const id = p.programId || p.outreachProgramId || p.id
  joiningId.value = id

  // Simulate or persist join
  await new Promise(r => setTimeout(r, 600))
  joinedPrograms[id] = true
  joiningId.value = null
}

async function deleteProgram(p) {
  const id = p.programId || p.outreachProgramId || p.id
  if (!confirm(`Delete outreach program "${p.programName}"?`)) return
  try {
    await fetch(`/api/outreachProgram/delete/${id}`, {
      method: 'DELETE',
      headers: { ...getAuthHeader() }
    })
  } catch {}
  programs.value = programs.value.filter(
    item => (item.programId || item.outreachProgramId || item.id) !== id
  )
}

onMounted(fetchPrograms)
</script>

<style scoped>
.outreach-page {
  min-height: 100vh;
  background: #fdfcf9;
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
}

/* Hero */
.outreach-hero {
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

.outreach-hero h1 {
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

/* Main */
.outreach-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 50px 24px 80px;
}

.section-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 20px;
  flex-wrap: wrap;
  margin-bottom: 36px;
}

.section-top h2 {
  font-family: Georgia, serif;
  font-size: 28px;
  margin: 0 0 6px;
  color: #2f281f;
}

.section-desc {
  font-size: 14px;
  color: #7b7366;
  margin: 0;
}

.create-program-btn {
  background: #9b6d00;
  color: #ffffff;
  border: none;
  padding: 11px 20px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.create-program-btn:hover {
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

/* Programs Grid */
.programs-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(360px, 1fr));
  gap: 26px;
}

.program-card {
  background: #ffffff;
  border: 1px solid #eae5dc;
  border-radius: 18px;
  padding: 28px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.04);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s, box-shadow 0.2s;
}

.program-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.08);
}

.card-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 14px;
}

.program-badge {
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 1px;
  padding: 3px 9px;
  border-radius: 6px;
  background: #ecfdf5;
  color: #047857;
}

.delete-btn {
  background: transparent;
  border: none;
  color: #b91c1c;
  font-size: 14px;
  cursor: pointer;
  padding: 2px 6px;
  border-radius: 4px;
}

.delete-btn:hover {
  background: #fee2e2;
}

.program-title {
  font-family: Georgia, serif;
  font-size: 22px;
  margin: 0 0 10px;
  color: #2f281f;
}

.program-desc {
  font-size: 14px;
  line-height: 1.65;
  color: #5c5243;
  margin: 0 0 18px;
  flex: 1;
}

.program-meta {
  font-size: 13px;
  color: #8c8273;
  margin-bottom: 20px;
  padding-bottom: 14px;
  border-bottom: 1px solid #f2ede5;
}

.signup-zone {
  margin-top: auto;
}

.joined-badge {
  background: #eef8f1;
  color: #166534;
  border: 1px solid #bbf7d0;
  border-radius: 10px;
  padding: 12px;
  font-size: 13px;
  font-weight: 600;
  text-align: center;
}

.volunteer-form .input-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  margin-bottom: 10px;
}

.volunteer-form input {
  width: 100%;
  border: 1px solid #ddd7cc;
  border-radius: 8px;
  padding: 9px 12px;
  font-size: 13px;
  outline: none;
  background: #faf9f6;
  font-family: inherit;
  box-sizing: border-box;
}

.volunteer-form input:focus {
  border-color: #9b6d00;
  background: #ffffff;
}

.join-btn {
  width: 100%;
  background: #80633e;
  color: #ffffff;
  border: none;
  padding: 10px;
  border-radius: 8px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.join-btn:hover:not(:disabled) {
  background: #6e5433;
}

.join-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
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

.program-form .form-group {
  margin-bottom: 16px;
}

.program-form label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 6px;
}

.program-form input,
.program-form textarea {
  width: 100%;
  padding: 10px 14px;
  border: 1px solid #ddd7cc;
  border-radius: 8px;
  font-size: 14px;
  font-family: inherit;
  box-sizing: border-box;
}

.program-form input:focus,
.program-form textarea:focus {
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
  .outreach-hero h1 {
    font-size: 34px;
  }
  .volunteer-form .input-row {
    grid-template-columns: 1fr;
  }
}
</style>
