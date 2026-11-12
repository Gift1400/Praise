<template>
  <div class="profile-page">

    <!-- Hero Header -->
    <section class="profile-hero">
      <div class="hero-overlay">
        <p class="eyebrow">PRAISE CHURCH COMMUNITY</p>
        <h1>Member Profile &amp; Portal</h1>
        <p class="hero-subtitle">
          Manage your personal details, review your church involvement, and stay connected with our community.
        </p>
      </div>
    </section>

    <!-- Guest / Not Logged In State -->
    <div v-if="!currentUser" class="content-container guest-state-wrap">
      <div class="guest-card">
        <div class="guest-icon">✝</div>
        <h2>Sign in to Access Your Profile</h2>
        <p>You need to be logged into your Praise Church account to view your member profile, RSVPs, and prayer
          requests.</p>
        <div class="guest-actions">
          <router-link to="/login" class="btn-primary">Sign In to Your Account</router-link>
          <router-link to="/signup" class="btn-outline">Create a New Account</router-link>
        </div>
      </div>
    </div>

    <!-- Logged In Member Profile -->
    <div v-else class="content-container profile-container">

      <!-- Leader Notice (Redirect to Dashboard) -->
      <div v-if="currentUser?.role === 'LEADER' || currentUser?.role === 'ADMIN'" class="leader-notice-banner">
        <div class="notice-content">
          <span class="notice-icon">🛡️</span>
          <div>
            <strong>Church Leader Portal Notice:</strong> Your church management tools, events, and ministry controls
            are located on your Staff Dashboard.
          </div>
        </div>
        <router-link to="/dashboard" class="btn-goto-dashboard">Go to Leadership Dashboard →</router-link>
      </div>

      <!-- Top Summary Header Card -->
      <section class="profile-header-card">
        <div class="avatar-and-info">
          <!-- Profile Vector Avatar Placeholder -->
          <div class="avatar-placeholder">
            <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8"
              stroke-linecap="round" stroke-linejoin="round">
              <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
              <circle cx="12" cy="7" r="4"></circle>
            </svg>
          </div>

          <div class="user-meta">
            <div class="name-row">
              <h2>{{ currentUser.firstName }} {{ currentUser.lastName || '' }}</h2>
              <span class="role-pill" :class="currentUser.role?.toLowerCase()">
                {{ currentUser.role === 'LEADER' ? 'Church Leader' : 'Church Member' }}
              </span>
            </div>
            <p class="user-email">{{ currentUser.email }}</p>
            <div class="sub-badges">
              <span class="status-badge">✓ Active Member</span>
              <span class="campus-badge">📍 Cape Town Campus</span>
            </div>
          </div>
        </div>

        <div class="header-actions">
          <button class="btn-signout" @click="handleLogout">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
              stroke-linecap="round" stroke-linejoin="round">
              <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path>
              <polyline points="16 17 21 12 16 7"></polyline>
              <line x1="21" y1="12" x2="9" y2="12"></line>
            </svg>
            Sign Out
          </button>
        </div>
      </section>

      <!-- Profile Navigation Tabs -->
      <nav class="profile-tabs">
        <button v-for="t in tabs" :key="t.id" class="tab-btn" :class="{ active: currentTab === t.id }"
          @click="currentTab = t.id">
          <span class="tab-icon">{{ t.icon }}</span>
          <span>{{ t.label }}</span>
        </button>
      </nav>

      <!-- TAB 1: Personal & Contact Details (UML ContactDetails + Member) -->
      <section v-if="currentTab === 'details'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>Personal &amp; Contact Information</h3>
            <p>Update your church contact records and communication preferences.</p>
          </div>
        </div>

        <form @submit.prevent="saveProfile" class="profile-form">
          <div class="form-grid-2">
            <div class="form-group">
              <label for="firstName">First Name</label>
              <input id="firstName" v-model="editForm.firstName" required />
            </div>

            <div class="form-group">
              <label for="lastName">Last Name</label>
              <input id="lastName" v-model="editForm.lastName" required />
            </div>
          </div>

          <div class="form-grid-2">
            <div class="form-group">
              <label for="email">Email Address</label>
              <input id="email" v-model="editForm.email" type="email" required />
            </div>

            <div class="form-group">
              <label for="username">Username / Member Tag</label>
              <input id="username" v-model="editForm.userName" placeholder="e.g. sarah_j" />
            </div>
          </div>

          <div class="form-grid-2">
            <div class="form-group">
              <label for="phone">Primary Phone Number</label>
              <input id="phone" v-model="editForm.phone" placeholder="+27 82 123 4567" />
            </div>

            <div class="form-group">
              <label for="altPhone">Alternative / Emergency Phone</label>
              <input id="altPhone" v-model="editForm.altPhone" placeholder="+27 83 987 6543" />
            </div>
          </div>

          <div class="form-group">
            <label for="address">Residential Address</label>
            <input id="address" v-model="editForm.address"
              placeholder="e.g. 14 Trinity Avenue, Rondebosch, Cape Town" />
          </div>

          <div class="form-group">
            <label for="churchSite">Home Church Campus</label>
            <select id="churchSite" v-model="editForm.churchSite">
              <option value="Holy Trinity Main Campus">Holy Trinity Main Campus (Cape Town)</option>
              <option value="Cape Town Central City">Cape Town Central City</option>
              <option value="Bellville Campus">Bellville Campus</option>
            </select>
          </div>

          <div v-if="saveMessage" class="alert-box success">
            {{ saveMessage }}
          </div>

          <div class="form-actions">
            <button type="submit" class="btn-save-profile" :disabled="saving">
              {{ saving ? 'Saving Changes...' : 'Save Profile Changes' }}
            </button>
          </div>
        </form>
      </section>

      <!-- TAB 2: My Event RSVPs -->
      <section v-if="currentTab === 'rsvps'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>My Event Gatherings &amp; RSVPs</h3>
            <p>Events you have signed up to attend with Praise Church.</p>
          </div>
          <router-link to="/events" class="link-btn">Browse Events →</router-link>
        </div>

        <div v-if="myRsvps.length === 0" class="empty-state-box">
          <p>You haven't RSVP'd to any upcoming events yet.</p>
          <router-link to="/events" class="btn-small-primary">View Upcoming Events</router-link>
        </div>

        <div v-else class="activity-list">
          <div v-for="r in myRsvps" :key="r.id" class="activity-item">
            <div class="item-icon-box">📅</div>
            <div class="item-content">
              <h4>{{ r.eventTitle }}</h4>
              <p class="item-meta">
                <span>📍 {{ r.location }}</span> · <span>🕒 {{ r.dateTime }}</span>
              </p>
            </div>
            <div class="item-status">
              <span class="status-badge-active">✓ Attending ({{ r.attendees }} seat{{ r.attendees > 1 ? 's' : ''
                }})</span>
            </div>
          </div>
        </div>
      </section>

      <!-- TAB 3: My Prayer Requests -->
      <section v-if="currentTab === 'prayers'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>My Prayer Requests</h3>
            <p>Requests you have shared with our church pastoral care team.</p>
          </div>
          <router-link to="/prayer" class="link-btn">New Prayer Request →</router-link>
        </div>

        <div v-if="myPrayers.length === 0" class="empty-state-box">
          <p>You have not submitted any prayer requests yet.</p>
          <router-link to="/prayer" class="btn-small-primary">Share a Request</router-link>
        </div>

        <div v-else class="activity-list">
          <div v-for="p in myPrayers" :key="p.id" class="activity-item">
            <div class="item-icon-box">🙏</div>
            <div class="item-content">
              <div class="item-header-tags">
                <span class="pill-mini" :class="{ private: p.isPrivate }">
                  {{ p.isPrivate ? '🔒 Confidential' : '🌐 Prayer Wall' }}
                </span>
                <span class="item-date">{{ p.date }}</span>
              </div>
              <p class="item-text">{{ p.content }}</p>
            </div>
          </div>
        </div>
      </section>

      <!-- TAB 4: My Community Outreach Teams -->
      <section v-if="currentTab === 'outreach'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>My Outreach &amp; Volunteering</h3>
            <p>Community outreach initiatives you are volunteering with.</p>
          </div>
          <router-link to="/outreach" class="link-btn">Explore Outreach →</router-link>
        </div>

        <div class="activity-list">
          <div class="activity-item">
            <div class="item-icon-box">🤝</div>
            <div class="item-content">
              <h4>Hope Food Pantry &amp; Soup Kitchen</h4>
              <p class="item-meta">Saturday Mornings · City Center Community Hall</p>
              <p class="item-text">Serving hot meals and preparing grocery packages for local families in need.</p>
            </div>
            <div class="item-status">
              <span class="status-badge-active">Active Volunteer</span>
            </div>
          </div>
        </div>
      </section>

      <!-- TAB 5: My Giving & Tithes -->
      <section v-if="currentTab === 'giving'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>My Contributions &amp; Giving History</h3>
            <p>Record of tithes, offerings, and special gifts given to Praise Church ministries.</p>
          </div>
          <router-link to="/giving" class="link-btn">Make a Contribution →</router-link>
        </div>

        <div class="giving-summary-row">
          <div class="summary-box">
            <span class="sum-label">Recorded Contributions</span>
            <strong class="sum-val">R {{ Number(givingTotal).toLocaleString('en-ZA') }}</strong>
          </div>
          <div class="summary-box">
            <span class="sum-label">Giving Statements</span>
            <span class="sum-sub">Year-to-date Tax Certificate Ready</span>
          </div>
        </div>

        <div class="activity-list">
          <div v-for="g in myGifts" :key="g.id" class="activity-item">
            <div class="item-icon-box">💰</div>
            <div class="item-content">
              <h4>{{ g.type }}</h4>
              <p class="item-meta">Date: {{ g.date }} · Method: {{ g.method }}</p>
            </div>
            <div class="item-status">
              <strong class="giving-amount">R {{ g.amount.toFixed(2) }}</strong>
            </div>
          </div>
        </div>
      </section>

      <!-- TAB 6: Account Security -->
      <section v-if="currentTab === 'security'" class="tab-content-card">
        <div class="tab-header">
          <div>
            <h3>Account Security</h3>
            <p>Manage your password and sign in credentials.</p>
          </div>
        </div>

        <form @submit.prevent="updatePassword" class="security-form">
          <div class="form-group">
            <label for="currentPassword">Current Password</label>
            <input id="currentPassword" v-model="securityForm.currentPassword" type="password" required />
          </div>

          <div class="form-group">
            <label for="newPassword">New Password</label>
            <input id="newPassword" v-model="securityForm.newPassword" type="password" minlength="6" required />
          </div>

          <div class="form-group">
            <label for="confirmNewPassword">Confirm New Password</label>
            <input id="confirmNewPassword" v-model="securityForm.confirmNewPassword" type="password" minlength="6"
              required />
          </div>

          <div v-if="passwordMessage" class="alert-box success">
            {{ passwordMessage }}
          </div>
          <div v-if="passwordError" class="alert-box error">
            {{ passwordError }}
          </div>

          <div class="form-actions">
            <button type="submit" class="btn-save-profile">
              Update Password
            </button>
          </div>
        </form>
      </section>

    </div>

  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getUser, logout as authLogout, isLeader } from '../util/auth'

const router = useRouter()
const currentUser = ref(null)
const currentTab = ref('details')
const saving = ref(false)
const saveMessage = ref('')

const passwordMessage = ref('')
const passwordError = ref('')

const tabs = [
  { id: 'details', label: 'My Details', icon: '👤' },
  { id: 'rsvps', label: 'My RSVPs', icon: '🎟️' },
  { id: 'prayers', label: 'My Prayers', icon: '🙏' },
  { id: 'outreach', label: 'Outreach Teams', icon: '🤝' },
  { id: 'giving', label: 'Giving Record', icon: '💰' },
  { id: 'security', label: 'Security', icon: '🔐' }
]

const editForm = reactive({
  firstName: '',
  lastName: '',
  email: '',
  userName: '',
  phone: '',
  altPhone: '',
  address: '',
  churchSite: 'Holy Trinity Main Campus'
})

const securityForm = reactive({
  currentPassword: '',
  newPassword: '',
  confirmNewPassword: ''
})

const myRsvps = ref([
  {
    id: 'r-1',
    eventTitle: 'Sunday Morning Celebration Service',
    location: 'Main Sanctuary',
    dateTime: 'Every Sunday · 09:00 AM',
    attendees: 2
  },
  {
    id: 'r-2',
    eventTitle: 'Night of Worship & Encounter',
    location: 'Holy Trinity Hall',
    dateTime: 'Friday 20 Nov · 18:30 PM',
    attendees: 1
  }
])

const myPrayers = ref([
  {
    id: 'p-1',
    content: 'Praying for healing and strength for my family, and for wisdom in navigating a new business venture.',
    isPrivate: true,
    date: '2026-11-06'
  },
  {
    id: 'p-2',
    content: 'Thanking God for open doors and new opportunities to serve our local community with joy.',
    isPrivate: false,
    date: '2026-11-02'
  }
])

const myGifts = ref([
  { id: 'g-1', type: 'Tithe (10%)', amount: 850, date: '2026-11-01', method: 'Debit Card' },
  { id: 'g-2', type: 'Outreach & Missions', amount: 300, date: '2026-10-18', method: 'EFT Transfer' },
  { id: 'g-3', type: 'General Offering', amount: 150, date: '2026-10-04', method: 'SnapScan' }
])

const givingTotal = computed(() => {
  return myGifts.value.reduce((sum, g) => sum + g.amount, 0)
})

function loadUserData() {
  const u = getUser()
  if (u) {
    currentUser.value = u
    editForm.firstName = u.firstName || ''
    editForm.lastName = u.lastName || ''
    editForm.email = u.email || ''
    editForm.userName = u.userName || (u.firstName ? u.firstName.toLowerCase() : 'member')
    editForm.phone = u.phone || '+27 82 555 0192'
    editForm.altPhone = u.altPhone || ''
    editForm.address = u.address || 'Cape Town, Western Cape'
    editForm.churchSite = u.churchSite || 'Holy Trinity Main Campus'
  }
}

async function saveProfile() {
  saving.value = true
  saveMessage.value = ''

  try {
    // Update local storage representation
    const updatedUser = {
      ...currentUser.value,
      firstName: editForm.firstName.trim(),
      lastName: editForm.lastName.trim(),
      email: editForm.email.trim(),
      userName: editForm.userName.trim(),
      phone: editForm.phone.trim(),
      altPhone: editForm.altPhone.trim(),
      address: editForm.address.trim(),
      churchSite: editForm.churchSite
    }

    const storage = localStorage.getItem('user') ? localStorage : sessionStorage
    storage.setItem('user', JSON.stringify(updatedUser))
    currentUser.value = updatedUser

    saveMessage.value = 'Profile information and contact details saved successfully!'
  } catch {
    saveMessage.value = 'Saved locally.'
  } finally {
    saving.value = false
    setTimeout(() => {
      saveMessage.value = ''
    }, 3500)
  }
}

function updatePassword() {
  passwordMessage.value = ''
  passwordError.value = ''

  if (securityForm.newPassword !== securityForm.confirmNewPassword) {
    passwordError.value = 'New passwords do not match.'
    return
  }

  passwordMessage.value = 'Password updated successfully!'
  securityForm.currentPassword = ''
  securityForm.newPassword = ''
  securityForm.confirmNewPassword = ''

  setTimeout(() => {
    passwordMessage.value = ''
  }, 3500)
}

function handleLogout() {
  authLogout()
  currentUser.value = null
  router.push('/')
  window.location.reload()
}

onMounted(() => {
  if (isLeader()) {
    router.replace('/dashboard')
    return
  }
  loadUserData()
})
</script>

<style scoped>
.profile-page {
  min-height: 100vh;
  background: #fdfcf9;
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
}

.content-container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 24px;
}

/* Hero */
.profile-hero {
  background:
    linear-gradient(rgba(20, 16, 10, 0.78), rgba(20, 16, 10, 0.88)),
    linear-gradient(135deg, #7c5f3b 0%, #2e2316 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 30px) 24px 50px;
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
  font-weight: 700;
  margin-bottom: 12px;
}

.profile-hero h1 {
  font-family: Georgia, serif;
  font-size: 42px;
  font-weight: 500;
  margin: 0 0 14px;
}

.hero-subtitle {
  font-size: 15px;
  line-height: 1.7;
  color: #e5ded4;
  margin: 0;
}

/* Guest State */
.guest-state-wrap {
  padding: 80px 24px;
  display: flex;
  justify-content: center;
}

.guest-card {
  background: #ffffff;
  border: 1px solid #e7e0d5;
  border-radius: 20px;
  padding: 50px 36px;
  max-width: 500px;
  text-align: center;
  box-shadow: 0 12px 35px rgba(0, 0, 0, 0.05);
}

.guest-icon {
  font-size: 36px;
  color: #9b6d00;
  margin-bottom: 18px;
}

.guest-card h2 {
  font-family: Georgia, serif;
  font-size: 26px;
  margin: 0 0 12px;
  color: #2f281f;
}

.guest-card p {
  font-size: 14.5px;
  color: #7b7366;
  line-height: 1.65;
  margin: 0 0 28px;
}

.guest-actions {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.btn-primary {
  background: #9b6d00;
  color: #ffffff;
  padding: 12px 24px;
  border-radius: 24px;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-primary:hover {
  background: #805a00;
}

.btn-outline {
  border: 1.5px solid #9b6d00;
  color: #9b6d00;
  padding: 11px 24px;
  border-radius: 24px;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
}

.btn-outline:hover {
  background: #fcf9f4;
}

/* Profile Container */
.profile-container {
  padding-top: 40px;
  padding-bottom: 90px;
}

/* Leader Notice Banner */
.leader-notice-banner {
  background: #fbf5e6;
  border: 1px solid #dfba73;
  border-radius: 16px;
  padding: 16px 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 24px;
  flex-wrap: wrap;
}

.notice-content {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #5c4308;
  font-size: 14.5px;
}

.notice-icon {
  font-size: 20px;
}

.btn-goto-dashboard {
  background: #9b6d00;
  color: #ffffff;
  padding: 8px 18px;
  border-radius: 20px;
  font-size: 13.5px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s;
  white-space: nowrap;
}

.btn-goto-dashboard:hover {
  background: #7d5700;
}

/* Header Card */
.profile-header-card {
  background: #ffffff;
  border: 1px solid #e8e3d8;
  border-radius: 20px;
  padding: 32px;
  box-shadow: 0 6px 25px rgba(0, 0, 0, 0.04);
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 24px;
  margin-bottom: 30px;
  flex-wrap: wrap;
}

.avatar-and-info {
  display: flex;
  align-items: center;
  gap: 22px;
}

.avatar-placeholder {
  width: 72px;
  height: 72px;
  border-radius: 50%;
  background: #9b6d00;
  color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 16px rgba(155, 109, 0, 0.35);
  flex-shrink: 0;
}

.name-row {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
}

.name-row h2 {
  font-family: Georgia, serif;
  font-size: 26px;
  margin: 0;
  color: #2f281f;
}

.role-pill {
  font-size: 11.5px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.8px;
  padding: 3px 10px;
  border-radius: 12px;
  background: #fbf5ea;
  color: #80633e;
}

.role-pill.leader {
  background: #9b6d00;
  color: #ffffff;
}

.user-email {
  font-size: 14px;
  color: #7a7061;
  margin: 4px 0 10px;
}

.sub-badges {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

.status-badge {
  font-size: 12px;
  color: #15803d;
  background: #ecfdf5;
  padding: 2px 8px;
  border-radius: 6px;
  font-weight: 600;
}

.campus-badge {
  font-size: 12px;
  color: #635948;
  background: #f5f0e6;
  padding: 2px 8px;
  border-radius: 6px;
}

.btn-signout {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  background: #fee2e2;
  color: #991b1b;
  border: 1px solid #fecaca;
  padding: 9px 18px;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  font-family: inherit;
}

.btn-signout:hover {
  background: #fca5a5;
}

/* Tabs */
.profile-tabs {
  display: flex;
  gap: 8px;
  border-bottom: 2px solid #eae5db;
  margin-bottom: 30px;
  overflow-x: auto;
  padding-bottom: 4px;
}

.tab-btn {
  display: inline-flex;
  align-items: center;
  gap: 7px;
  border: none;
  background: transparent;
  padding: 11px 18px;
  border-radius: 10px 10px 0 0;
  font-size: 14px;
  font-weight: 600;
  color: #6d6455;
  cursor: pointer;
  white-space: nowrap;
  font-family: inherit;
  transition: all 0.2s;
}

.tab-btn:hover {
  background: #f8f4ec;
  color: #2f281f;
}

.tab-btn.active {
  color: #9b6d00;
  border-bottom: 3px solid #9b6d00;
  background: #ffffff;
}

/* Tab Card */
.tab-content-card {
  background: #ffffff;
  border: 1px solid #eae5dc;
  border-radius: 18px;
  padding: 36px;
  box-shadow: 0 6px 25px rgba(0, 0, 0, 0.03);
}

.tab-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 28px;
  flex-wrap: wrap;
  gap: 12px;
}

.tab-header h3 {
  font-family: Georgia, serif;
  font-size: 22px;
  margin: 0 0 6px;
  color: #2f281f;
}

.tab-header p {
  font-size: 13.5px;
  color: #7b7366;
  margin: 0;
}

.link-btn {
  color: #9b6d00;
  font-size: 13.5px;
  font-weight: 600;
  text-decoration: none;
}

.link-btn:hover {
  text-decoration: underline;
}

/* Forms */
.form-grid-2 {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #3d372f;
  margin-bottom: 8px;
}

.profile-form input,
.profile-form select,
.security-form input {
  width: 100%;
  height: 46px;
  border: 1px solid #ddd7cc;
  border-radius: 10px;
  padding: 0 14px;
  font-size: 14px;
  outline: none;
  background: #faf9f6;
  font-family: inherit;
  box-sizing: border-box;
  transition: all 0.2s;
}

.profile-form input:focus,
.profile-form select:focus,
.security-form input:focus {
  border-color: #9b6d00;
  background: #ffffff;
}

.form-actions {
  margin-top: 10px;
}

.btn-save-profile {
  background: #9b6d00;
  color: #ffffff;
  border: none;
  padding: 13px 28px;
  border-radius: 10px;
  font-size: 14.5px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  box-shadow: 0 4px 12px rgba(155, 109, 0, 0.25);
  font-family: inherit;
}

.btn-save-profile:hover:not(:disabled) {
  background: #805a00;
}

.btn-save-profile:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.alert-box {
  padding: 12px 16px;
  border-radius: 10px;
  font-size: 13.5px;
  margin-bottom: 18px;
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

/* Activity Items */
.activity-list {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.activity-item {
  display: flex;
  align-items: flex-start;
  gap: 16px;
  background: #faf8f4;
  border: 1px solid #eae5db;
  border-radius: 14px;
  padding: 20px;
}

.item-icon-box {
  width: 44px;
  height: 44px;
  border-radius: 10px;
  background: #ffffff;
  border: 1px solid #e2ddd3;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  flex-shrink: 0;
}

.item-content {
  flex: 1;
}

.item-content h4 {
  font-family: Georgia, serif;
  font-size: 17px;
  margin: 0 0 4px;
  color: #2f281f;
}

.item-meta {
  font-size: 13px;
  color: #7b7366;
  margin: 0 0 6px;
}

.item-text {
  font-size: 13.5px;
  color: #554b3d;
  line-height: 1.5;
  margin: 0;
}

.item-header-tags {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 6px;
}

.pill-mini {
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  padding: 2px 8px;
  border-radius: 6px;
  background: #e0f2fe;
  color: #0369a1;
}

.pill-mini.private {
  background: #fef3c7;
  color: #92400e;
}

.item-date {
  font-size: 12px;
  color: #8c8273;
}

.status-badge-active {
  font-size: 12px;
  font-weight: 600;
  color: #047857;
  background: #ecfdf5;
  padding: 4px 10px;
  border-radius: 8px;
  white-space: nowrap;
}

.giving-summary-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 18px;
  margin-bottom: 24px;
}

.summary-box {
  background: #faf8f3;
  border: 1px solid #e8e3d8;
  border-left: 4px solid #9b6d00;
  border-radius: 12px;
  padding: 18px 22px;
}

.sum-label {
  display: block;
  font-size: 12.5px;
  color: #7b7366;
  margin-bottom: 4px;
}

.sum-val {
  font-family: Georgia, serif;
  font-size: 24px;
  color: #2f281f;
}

.sum-sub {
  font-size: 13px;
  color: #047857;
  font-weight: 600;
}

.giving-amount {
  font-family: Georgia, serif;
  font-size: 18px;
  color: #9b6d00;
}

.empty-state-box {
  text-align: center;
  padding: 40px;
  background: #faf8f4;
  border: 1px dashed #ded8cb;
  border-radius: 12px;
}

.empty-state-box p {
  color: #7b7366;
  font-size: 14px;
  margin: 0 0 16px;
}

.btn-small-primary {
  display: inline-block;
  background: #9b6d00;
  color: #ffffff;
  padding: 8px 18px;
  border-radius: 18px;
  font-size: 13px;
  font-weight: 600;
  text-decoration: none;
}

@media (max-width: 768px) {
  .form-grid-2 {
    grid-template-columns: 1fr;
    gap: 0;
  }

  .profile-header-card {
    flex-direction: column;
    align-items: flex-start;
  }

  .avatar-and-info {
    flex-direction: column;
    align-items: flex-start;
  }

  .giving-summary-row {
    grid-template-columns: 1fr;
  }
}
</style>
