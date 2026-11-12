<template>
  <div class="dashboard-page">

    <!-- Hero Header -->
    <section class="dashboard-hero">
      <div class="hero-overlay">
        <div class="hero-brand">
          <span class="cross-icon">✝</span>
          <span class="eyebrow">LEADERSHIP CONSOLE</span>
        </div>
        <h1>Staff &amp; Ministry Dashboard</h1>
        <p class="hero-subtitle">
          Manage congregation events, announcements, prayer requests, outreach initiatives, and ministry stewardship.
        </p>
      </div>
    </section>

    <!-- Guard for Non-Leaders -->
    <div v-if="!canManage" class="restricted-container">
      <div class="restricted-card">
        <div class="lock-icon">🔒</div>
        <h2>Leadership Portal Restricted</h2>
        <p>This administrative console is reserved for Praise Church pastors and leaders. If you are a church member, please visit your Member Profile.</p>
        <div class="restricted-actions">
          <router-link to="/profile" class="btn-home">Go to Member Profile</router-link>
          <router-link to="/events" class="btn-sub">View Events</router-link>
        </div>
      </div>
    </div>

    <!-- Leader Dashboard Interface -->
    <div v-else class="dashboard-container">

      <!-- Functional Ministry Management Cards (Click to Manage) -->
      <div class="section-title-bar">
        <h3>Leadership Ministry Control Center</h3>
        <p>Click any card to directly create, edit, and manage that area of ministry.</p>
      </div>

      <section class="overview-grid">
        <!-- 1. Events -->
        <router-link to="/events" class="metric-card" title="Go to Events page to create, edit, or delete events">
          <div class="card-top-row">
            <span class="card-icon-tag">📅</span>
            <span class="action-tag">Manage Events</span>
          </div>
          <span class="metric-title">Church Events</span>
          <strong class="metric-val">{{ counts.events }} Active</strong>
          <span class="metric-link">Create &amp; Manage Events →</span>
        </router-link>

        <!-- 2. Announcements -->
        <router-link to="/announcements" class="metric-card" title="Go to Announcements page to publish or delete notices">
          <div class="card-top-row">
            <span class="card-icon-tag">📢</span>
            <span class="action-tag">Manage Notices</span>
          </div>
          <span class="metric-title">Announcements</span>
          <strong class="metric-val">{{ counts.announcements }} Published</strong>
          <span class="metric-link">Post &amp; Edit Notices →</span>
        </router-link>

        <!-- 3. Outreach -->
        <router-link to="/outreach" class="metric-card" title="Go to Outreach page to launch programs and manage volunteers">
          <div class="card-top-row">
            <span class="card-icon-tag">🤝</span>
            <span class="action-tag">Lead Teams</span>
          </div>
          <span class="metric-title">Outreach Programs</span>
          <strong class="metric-val">{{ counts.outreach }} Active</strong>
          <span class="metric-link">Create &amp; Lead Programs →</span>
        </router-link>

        <!-- 4. Prayer & Testimonies -->
        <router-link to="/prayer" class="metric-card" title="Go to Prayer Wall to review requests and testimonies">
          <div class="card-top-row">
            <span class="card-icon-tag">🙏</span>
            <span class="action-tag">Pastoral Care</span>
          </div>
          <span class="metric-title">Prayer &amp; Testimonies</span>
          <strong class="metric-val">{{ counts.prayers }} Submissions</strong>
          <span class="metric-link">Review &amp; Moderate Wall →</span>
        </router-link>

        <!-- 5. Giving / Stewardship -->
        <router-link to="/giving" class="metric-card" title="Go to Giving portal to view total donations and ledger">
          <div class="card-top-row">
            <span class="card-icon-tag">💰</span>
            <span class="action-tag">Financials</span>
          </div>
          <span class="metric-title">Ministry Stewardship</span>
          <strong class="metric-val">R {{ Number(counts.giving).toLocaleString('en-ZA') }}</strong>
          <span class="metric-link">View Giving Ledger →</span>
        </router-link>

        <!-- 6. Scripture Verses -->
        <router-link to="/verses" class="metric-card" title="Go to Scripture finder to add or curate life season verses">
          <div class="card-top-row">
            <span class="card-icon-tag">📖</span>
            <span class="action-tag">Manage Verses</span>
          </div>
          <span class="metric-title">Scripture Verses</span>
          <strong class="metric-val">{{ counts.verses }} Promises</strong>
          <span class="metric-link">Add &amp; Curate Verses →</span>
        </router-link>
      </section>

      <!-- Dashboard Nav Tabs -->
      <nav class="dashboard-tabs">
        <button
          v-for="tab in tabs"
          :key="tab.id"
          class="d-tab"
          :class="{ active: activeTab === tab.id }"
          @click="activeTab = tab.id"
        >
          <span class="tab-icon">{{ tab.icon }}</span>
          <span>{{ tab.label }}</span>
        </button>
      </nav>

      <!-- Active Tab Panel Content -->
      <section class="tab-panel">

        <!-- 1. Events Panel -->
        <div v-if="activeTab === 'events'" class="panel-box">
          <div class="panel-header">
            <h3>Events Management</h3>
            <router-link to="/events" class="panel-btn">Go to Events Page</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Title</th>
                  <th>Date &amp; Time</th>
                  <th>Location</th>
                  <th>Expected</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="e in eventsList" :key="e.eventId">
                  <td><strong>{{ e.title }}</strong></td>
                  <td>{{ e.date }} {{ e.time ? '· ' + e.time : '' }}</td>
                  <td>{{ e.location }}</td>
                  <td>{{ e.expectedAttendees || 0 }} people</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 2. RSVPs Panel -->
        <div v-if="activeTab === 'rsvps'" class="panel-box">
          <div class="panel-header">
            <h3>Event Attendance &amp; RSVPs</h3>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>RSVP ID</th>
                  <th>Status</th>
                  <th>Event</th>
                  <th>Member / Attendee</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="r in rsvpList" :key="r.rsvpId || r.id">
                  <td><code>{{ r.rsvpId?.slice(0, 8) || 'RSVP-1' }}</code></td>
                  <td><span class="status-pill active">{{ r.status || 'ATTENDING' }}</span></td>
                  <td>{{ r.event?.title || 'Sunday Worship Service' }}</td>
                  <td>{{ r.member?.userName || 'Registered Attendee' }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 3. Announcements Panel -->
        <div v-if="activeTab === 'announcements'" class="panel-box">
          <div class="panel-header">
            <h3>Church Announcements</h3>
            <router-link to="/announcements" class="panel-btn">Manage Announcements</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Title</th>
                  <th>Category</th>
                  <th>Date</th>
                  <th>Message Preview</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="a in announcementsList" :key="a.announcementId || a.id">
                  <td><strong>{{ a.title }}</strong></td>
                  <td><span class="status-pill">{{ a.type || 'GENERAL' }}</span></td>
                  <td>{{ a.date }}</td>
                  <td class="truncate-cell">{{ a.message }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 4. Donations Panel -->
        <div v-if="activeTab === 'donations'" class="panel-box">
          <div class="panel-header">
            <h3>Contributions &amp; Stewardship</h3>
            <router-link to="/giving" class="panel-btn">Giving Portal</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Donor</th>
                  <th>Designation</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="d in donationList" :key="d.donation_id || d.id">
                  <td>{{ d.donation_date }}</td>
                  <td>{{ d.donor_name || 'Anonymous' }}</td>
                  <td><span class="status-pill">{{ d.donation_type }}</span></td>
                  <td><strong>R {{ Number(d.amount).toFixed(2) }}</strong></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 5. Prayer & Testimonies Panel -->
        <div v-if="activeTab === 'prayer'" class="panel-box">
          <div class="panel-header">
            <h3>Prayer Wall &amp; Pastoral Care</h3>
            <router-link to="/prayer" class="panel-btn">Open Prayer Wall</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Type</th>
                  <th>Submitted By</th>
                  <th>Date</th>
                  <th>Confidentiality</th>
                  <th>Message</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="p in prayerList" :key="p.requestId || p.id">
                  <td><span class="status-pill">{{ p.kind || 'Prayer' }}</span></td>
                  <td>{{ p.author || 'Anonymous' }}</td>
                  <td>{{ p.date }}</td>
                  <td>{{ p.isPrivate ? '🔒 Private' : '🌐 Public' }}</td>
                  <td class="truncate-cell">{{ p.content }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 6. Outreach Panel -->
        <div v-if="activeTab === 'outreach'" class="panel-box">
          <div class="panel-header">
            <h3>Outreach Teams</h3>
            <router-link to="/outreach" class="panel-btn">Manage Outreach</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Program Name</th>
                  <th>Leader in Charge</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="o in outreachList" :key="o.programId || o.id">
                  <td><strong>{{ o.programName }}</strong></td>
                  <td>{{ o.leaderName || 'Pastoral Outreach' }}</td>
                  <td class="truncate-cell">{{ o.description }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 7. Verses Panel -->
        <div v-if="activeTab === 'verses'" class="panel-box">
          <div class="panel-header">
            <h3>Curated Bible Verses</h3>
            <router-link to="/verses" class="panel-btn">Scripture Finder</router-link>
          </div>
          <div class="table-wrap">
            <table class="data-table">
              <thead>
                <tr>
                  <th>Life Season</th>
                  <th>Reference</th>
                  <th>Verse Text</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="v in versesList" :key="v.verseId || v.id">
                  <td><span class="status-pill">{{ v.lifeSituation }}</span></td>
                  <td><strong>{{ v.book }} {{ v.chapter }}:{{ v.verseNumber }}</strong></td>
                  <td class="truncate-cell">{{ v.text }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

      </section>

    </div>

  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { isLeader, getAuthHeader } from '../util/auth'

const canManage = computed(() => isLeader())
const activeTab = ref('events')

const tabs = [
  { id: 'events', label: 'Events', icon: '📅' },
  { id: 'rsvps', label: 'RSVPs', icon: '🎟️' },
  { id: 'announcements', label: 'Announcements', icon: '📢' },
  { id: 'donations', label: 'Donations', icon: '💰' },
  { id: 'prayer', label: 'Prayer & Testimonies', icon: '🙏' },
  { id: 'outreach', label: 'Outreach', icon: '🤝' },
  { id: 'verses', label: 'Bible Verses', icon: '📖' }
]

const counts = reactive({
  events: 4,
  rsvps: 28,
  announcements: 3,
  giving: 18450,
  prayers: 12,
  outreach: 3,
  verses: 9
})

const eventsList = ref([])
const rsvpList = ref([
  { rsvpId: 'rsvp-01', status: 'ATTENDING', event: { title: 'Sunday Celebration Service' }, member: { userName: 'Sarah Jenkins' } },
  { rsvpId: 'rsvp-02', status: 'ATTENDING', event: { title: 'Midweek Worship & Prayer' }, member: { userName: 'David Khumalo' } },
  { rsvpId: 'rsvp-03', status: 'ATTENDING', event: { title: 'Youth Fellowship Night' }, member: { userName: 'Grace Thompson' } }
])
const announcementsList = ref([])
const donationList = ref([])
const prayerList = ref([])
const outreachList = ref([])
const versesList = ref([])

async function loadData() {
  try {
    const [eRes, aRes, dRes, pRes, oRes, vRes] = await Promise.allSettled([
      fetch('/api/events/getAll'),
      fetch('/api/announcements/getAll'),
      fetch('/api/donations/getAll', { headers: { ...getAuthHeader() } }),
      fetch('/api/prayerRequest/getAll'),
      fetch('/api/outreachProgram/getAll'),
      fetch('/api/bibleVerse/getAll')
    ])

    if (eRes.status === 'fulfilled' && eRes.value.ok) {
      const data = await eRes.value.json()
      if (Array.isArray(data)) {
        eventsList.value = data
        counts.events = data.length
      }
    }
    if (aRes.status === 'fulfilled' && aRes.value.ok) {
      const data = await aRes.value.json()
      if (Array.isArray(data)) {
        announcementsList.value = data
        counts.announcements = data.length
      }
    }
    if (dRes.status === 'fulfilled' && dRes.value.ok) {
      const data = await dRes.value.json()
      if (Array.isArray(data)) {
        donationList.value = data
        counts.giving = data.reduce((sum, item) => sum + (Number(item.amount) || 0), 0)
      }
    }
    if (pRes.status === 'fulfilled' && pRes.value.ok) {
      const data = await pRes.value.json()
      if (Array.isArray(data)) {
        prayerList.value = data.map(p => ({
          requestId: p.requestId,
          kind: 'PRAYER',
          content: p.content,
          author: p.member?.userName || 'Anonymous',
          date: p.date,
          isPrivate: p.isPrivate
        }))
        counts.prayers = data.length
      }
    }
    if (oRes.status === 'fulfilled' && oRes.value.ok) {
      const data = await oRes.value.json()
      if (Array.isArray(data)) {
        outreachList.value = data
        counts.outreach = data.length
      }
    }
    if (vRes.status === 'fulfilled' && vRes.value.ok) {
      const data = await vRes.value.json()
      if (Array.isArray(data)) {
        versesList.value = data
        counts.verses = data.length
      }
    }
  } catch {}
}

onMounted(() => {
  if (canManage.value) {
    loadData()
  }
})
</script>

<style scoped>
.dashboard-page {
  min-height: 100vh;
  background: #fdfcf9;
  font-family: 'Poppins', 'Segoe UI', sans-serif;
  color: #2f281f;
}

/* Hero */
.dashboard-hero {
  background:
    linear-gradient(rgba(20, 16, 10, 0.8), rgba(20, 16, 10, 0.9)),
    linear-gradient(135deg, #7c5f3b 0%, #2e2316 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: calc(12vh + 30px) 24px 50px;
}

.hero-overlay {
  max-width: 760px;
  color: #ffffff;
}

.hero-brand {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 12px;
}

.cross-icon {
  color: #dfba73;
  font-size: 16px;
}

.eyebrow {
  font-size: 13px;
  letter-spacing: 3px;
  text-transform: uppercase;
  color: #dfba73;
  font-weight: 600;
}

.dashboard-hero h1 {
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

/* Restricted */
.restricted-container {
  display: flex;
  justify-content: center;
  padding: 80px 24px;
}

.restricted-card {
  background: #ffffff;
  border: 1px solid #e7e0d5;
  border-radius: 18px;
  padding: 44px;
  max-width: 480px;
  text-align: center;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
}

.lock-icon {
  font-size: 40px;
  margin-bottom: 16px;
}

.restricted-card h2 {
  font-family: Georgia, serif;
  font-size: 24px;
  margin: 0 0 10px;
}

.restricted-card p {
  color: #7b7366;
  font-size: 14px;
  margin: 0 0 24px;
}

.restricted-actions {
  display: flex;
  justify-content: center;
  gap: 12px;
  flex-wrap: wrap;
}

.btn-home {
  display: inline-block;
  background: #9b6d00;
  color: #ffffff;
  padding: 11px 22px;
  border-radius: 8px;
  text-decoration: none;
  font-weight: 600;
  font-size: 14px;
  transition: background 0.2s;
}

.btn-home:hover {
  background: #7d5700;
}

.btn-sub {
  display: inline-block;
  background: #f4efe6;
  color: #5c4308;
  border: 1px solid #dfba73;
  padding: 11px 22px;
  border-radius: 8px;
  text-decoration: none;
  font-weight: 600;
  font-size: 14px;
  transition: all 0.2s;
}

.btn-sub:hover {
  background: #eae2d3;
}

/* Dashboard Content */
.dashboard-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 24px 80px;
}

.overview-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(210px, 1fr));
  gap: 16px;
  margin-bottom: 36px;
}

.metric-card {
  background: #ffffff;
  border: 1px solid #e8e3d8;
  border-radius: 14px;
  padding: 22px;
  cursor: pointer;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.03);
  transition: transform 0.2s, border-color 0.2s;
}

.metric-card:hover {
  transform: translateY(-2px);
  border-color: #9b6d00;
}

.metric-title {
  display: block;
  font-size: 13px;
  color: #7b7366;
  margin-bottom: 6px;
}

.metric-val {
  display: block;
  font-family: Georgia, serif;
  font-size: 26px;
  color: #2f281f;
  margin-bottom: 8px;
}

.metric-link {
  font-size: 12px;
  color: #9b6d00;
  font-weight: 600;
}

/* Tabs */
.dashboard-tabs {
  display: flex;
  gap: 8px;
  border-bottom: 2px solid #eae5dc;
  margin-bottom: 30px;
  overflow-x: auto;
  padding-bottom: 4px;
}

.d-tab {
  display: inline-flex;
  align-items: center;
  gap: 7px;
  border: none;
  background: transparent;
  padding: 10px 18px;
  border-radius: 8px 8px 0 0;
  font-size: 14px;
  font-weight: 600;
  color: #6d6455;
  cursor: pointer;
  white-space: nowrap;
  font-family: inherit;
  transition: all 0.2s;
}

.d-tab:hover {
  color: #2f281f;
  background: #f7f3ec;
}

.d-tab.active {
  color: #9b6d00;
  border-bottom: 3px solid #9b6d00;
  background: #fbf9f4;
}

/* Panel */
.panel-box {
  background: #ffffff;
  border: 1px solid #eae5db;
  border-radius: 16px;
  padding: 28px;
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.03);
}

.panel-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 22px;
}

.panel-header h3 {
  font-family: Georgia, serif;
  font-size: 22px;
  margin: 0;
  color: #2f281f;
}

.panel-btn {
  background: #faf6f0;
  border: 1px solid #e0d7c7;
  color: #80633e;
  padding: 8px 16px;
  border-radius: 6px;
  text-decoration: none;
  font-size: 13px;
  font-weight: 600;
  transition: all 0.2s;
}

.panel-btn:hover {
  background: #80633e;
  color: #ffffff;
}

.table-wrap {
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
  font-size: 13.5px;
}

.data-table th {
  padding: 12px 14px;
  border-bottom: 2px solid #eae5db;
  color: #7b7366;
  font-size: 12.5px;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.data-table td {
  padding: 13px 14px;
  border-bottom: 1px solid #f2ede5;
}

.truncate-cell {
  max-width: 320px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.status-pill {
  background: #fbf5ea;
  color: #80633e;
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  padding: 3px 8px;
  border-radius: 6px;
}

.status-pill.active {
  background: #ecfdf5;
  color: #047857;
}

.section-title-bar {
  margin-bottom: 22px;
}

.section-title-bar h3 {
  font-family: Georgia, serif;
  font-size: 22px;
  color: #2f281f;
  margin: 0 0 6px;
}

.section-title-bar p {
  font-size: 14px;
  color: #7b7366;
  margin: 0;
}

.metric-card {
  text-decoration: none;
  display: flex;
  flex-direction: column;
}

.card-top-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;
}

.card-icon-tag {
  font-size: 24px;
}

.action-tag {
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  background: #fbf5ea;
  color: #9b6d00;
  padding: 3px 8px;
  border-radius: 6px;
}

.metric-card:hover .action-tag {
  background: #9b6d00;
  color: #ffffff;
}

</style>
