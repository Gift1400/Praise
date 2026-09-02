<template>
    <div class="home">
        <section class="hero-section">
            <section class="hero-image">
                <img src="../assets/images/home-bgr.png" id="hero-background" alt="hero image" />
            </section>

            <section class="hero-overlay">
                <h2 id="hero-title">Praise</h2>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nulla accusantium doloribus in. Reiciendis
                    rem
                    sit beatae maxime doloremque eum excepturi ipsa voluptas, ducimus ipsam vitae amet architecto,
                    fugiat
                    corporis in!</p>
                <button id="join">Join</button>
            </section>

        </section>


        <section class="announcements">
            <h2 id="announcements-title">
                Announcements
            </h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </section>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>

        <aside class="events">
            <h2 id="events-title">Events</h2>
            <h2></h2>
            <h2></h2>
            <h2></h2>
        </aside>
        <article class="layout">
            <aside class="events-layout1">
                <h2 id="events-layout1-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
            <aside class="events-layout2">
                <h2 id="events-layout2-title">Events</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2>
            </aside>
        </article>
    </div>

</template>

<style>
.home {
color:black;
}

.test {
    color: black;
}

.hero-section {
    display:grid;
    width: 100%;
}

.hero-image {
    display: flex;
    position: relative;
    max-width: 100dvw;
    height: 100dvh;
    overflow: hidden;
    top: -11dvh;
    left: 0;
}


#hero-background {
    display: flex;
    position: absolute;
    width: 100%;
    height: auto;
    object-fit: fill;
    filter: blur(1px) brightness(70%) saturate(120%);
    margin: 0 0 60vh 0;
    animation: zoom linear both;
    animation-timeline: scroll(root);
    animation-range: 0px 10px;
}

@keyframes zoom {
    to {
        transform: translateZ(40dvh);
    }

}

.hero-overlay {
    position: absolute;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    padding: 20px 0 0 0;
    width: 60dvw;
    height: 90dvh;
    background:linear-gradient(to right,black, rgba(174, 64, 64, 0));

    #hero-title {
        font-family: "League Gothic", sans-serif;
        font-size: 5rem;
        font-weight: 700;
        font-display: block;
        letter-spacing: 5px;
        color: white;
        padding: 0 0 0 5vh;

    }

    p {
        font-family: "League Gothic", sans-serif;
        font-size: 2.3rem;
        font-weight:lighter;
        padding: 0 0 0 5vh;
        color:white;

    }

    #join {
        position:relative;
        top:5vh;
        width: 18dvw;
        height: 10dvh;
        align-self: center;
        border: none;
        border-radius: 30px;
        font-size: 1.5rem;
        font-weight: 400;
    }

}

.announcements {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 80dvw;
    height: 40dvh;
    justify-self: center;

    #announcements-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}

.events {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 80dvw;
    height: 20dvh;
    justify-self: center;

    #events-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}

.layout {
    display: flex;
    overflow: hidden;
    border: 1px solid blue;
    width: 80dvw;
    height: 40dvh;
    justify-self: center;
}

.events-layout1 {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 60dvw;
    height: 40dvh;
    justify-self: center;
    flex: 2;

    #events-layout1-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}

.events-layout2 {
    display: flexbox;
    border: 1px solid black;
    border-radius: 20px;
    width: 40dvw;
    height: 40dvh;
    justify-self: center;
    flex: 1;

    #events-layout2-text {
        position: relative;
        color: black;
        font-size: 1rem;
        font-weight: 500;
        text-align: left;
    }
}
</style>